import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Engine implements Runnable {
    private final EntityManager entityManager;
    private BufferedReader bufferedReader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        System.out.println("Enter exercise number");

        try {
            int exerciseNumber = Integer.parseInt(this.bufferedReader.readLine());

            switch (exerciseNumber) {
                case 2:
                    changeCasing();
                    break;
                case 3:
                    containsEmployee();
                    break;
                case 4:
                    getEmployeesWithSalaryAbove();
                    break;
                case 5:
                    getEmployeesFromDepartment();
                    break;
                case 6:
                    addNewAddressAndUpdateEmployee();
                    break;
                case 7:
                    getAddressesWithEmployeeCount();
                    break;
                case 8:
                    getEmployeeWithProject();
                    break;
                case 9:
                    findLatestTenProjects();
                    break;
                case 10:
                    increaseSalaries();
                    break;
                case 11:
                    findEmployeeByFirstName();
                    break;
                case 12:
                    getEmployeesMaximumSalaries();
                    break;
                case 13:
                    removeTowns();
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }

    }

    private void removeTowns() throws IOException {
        System.out.println("Please enter town name");
        String townName = this.bufferedReader.readLine();

        Town town = this.entityManager.createQuery(
                        "SELECT t FROM Town t " +
                                "WHERE t.name = :t_name", Town.class)
                .setParameter("t_name", townName)
                .getSingleResult();

        int deletedAddresses = removeAllAddressesRelatedToTheGivenTown(town.getId());

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(town);
        this.entityManager.getTransaction().commit();

        System.out.printf("%d addresses in %s deleted%n", deletedAddresses, townName);
    }

    @SuppressWarnings("unchecked")
    private void getEmployeesMaximumSalaries() {
        /**
         * Пример за Native Query, но не е добра практика, защото при смяна на базата, трябва да бъдат пренаписани
         * и самите заявки. Затова е по-добре да се използва JPQL.
         */
        List<Object[]> resultList = this.entityManager.createNativeQuery("SELECT d.`name`, MAX(e.`salary`) AS 'max_salary' " +
                "FROM `departments` AS d " +
                "JOIN `employees` AS e " +
                "ON d.`department_id` = e.`department_id` " +
                "GROUP BY d.`name` " +
                "HAVING `max_salary` NOT BETWEEN 30000 AND 70000;").getResultList();

        for (Object[] arr : resultList) {
            System.out.printf("%s %.2f%n", arr[0], arr[1]);
        }
    }

    private void findEmployeeByFirstName() throws IOException {
        System.out.println("Please enter starting letters");
        String pattern = this.bufferedReader.readLine();
        String likeClause = pattern + "%";

        this.entityManager.createQuery(
                "SELECT e FROM Employee e " +
                        "WHERE e.firstName LIKE :s_letters", Employee.class)
                .setParameter("s_letters", likeClause)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getJobTitle(),
                        e.getSalary()));
    }

    private void increaseSalaries() {
        this.entityManager.getTransaction().begin();

        /**
         * Пример за параметър, който е Set от стойности.
         */
        int affectedRows = this.entityManager.createQuery(
                        "UPDATE Employee e " +
                                "SET e.salary = e.salary * 1.2" +
                                "WHERE e.department.id IN :ids")
                .setParameter("ids", Set.of(1, 2, 4, 11))
                .executeUpdate();

        this.entityManager.getTransaction().commit();
        System.out.println(affectedRows);
    }

    private void findLatestTenProjects() {
        List<Project> projects = this.entityManager.createQuery(
                        "SELECT p FROM Project p " +
                                "ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

        projects.stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(project -> {
                    System.out.printf("Project name: %s%n", project.getName());
                    System.out.printf("Project Description: %s%n", project.getDescription());
                    System.out.printf("Project Start Date: %s%n", project.getStartDate().toString());
                    System.out.printf("Project End Date: %s%n", project.getEndDate() == null ? "null" : project.getEndDate().toString());
                });
    }

    private void getEmployeeWithProject() throws IOException {
        System.out.println("Please enter id of the searching employee");
        int employeeId = Integer.parseInt(this.bufferedReader.readLine());

        Employee employee = this.entityManager.find(Employee.class, employeeId);
        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        employee.getProjects()
                .stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(p -> System.out.println(p.getName()));
    }

    private void getAddressesWithEmployeeCount() {
        List<Address> resultList = this.entityManager.createQuery(
                        "SELECT a FROM Address a " +
                                "ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        resultList.stream()
                .forEach(address -> System.out.printf("%s, %s - %d employees%n",
                        address.getText(),
                        address.getTown() == null ? "Unknown" : address.getTown().getName(),
                        address.getEmployees().size()));
    }

    private void addNewAddressAndUpdateEmployee() throws IOException {
        System.out.println("Please enter the new address");
        String newAddress = this.bufferedReader.readLine();

        Address address = createNewAddress(newAddress);

        System.out.println("Please enter last name of the searching employee");
        String lastName = this.bufferedReader.readLine();

        Employee employee = this.entityManager.createQuery(
                        "SELECT e FROM Employee e " +
                                "WHERE e.lastName = :last_name", Employee.class)
                .setParameter("last_name", lastName)
                .getSingleResult();

        this.entityManager.getTransaction().begin();
        employee.setAddress(address);
        this.entityManager.persist(employee);
        this.entityManager.getTransaction().commit();
    }

    private void getEmployeesFromDepartment() throws IOException {
        System.out.println("Please enter department name");
        String departmentName = this.bufferedReader.readLine();

        this.entityManager.createQuery(
                "SELECT e FROM Employee e " +
                        "WHERE e.department.name = :department_name " +
                        "ORDER BY e.salary ASC, e.id ASC", Employee.class)
                .setParameter("department_name", departmentName)
                .getResultStream()
                .forEach(employee -> System.out.printf("%s %s from %s - $%.2f%n",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDepartment().getName(),
                        employee.getSalary()));
    }

    private void getEmployeesWithSalaryAbove() throws IOException {
        System.out.println("Please enter minimum salary");
        BigDecimal minSalary = new BigDecimal(Long.parseLong(this.bufferedReader.readLine()));

        this.entityManager.createQuery(
                        "SELECT e FROM Employee e " +
                                "WHERE e.salary > :min_salary", Employee.class)
                .setParameter("min_salary", minSalary)
                .getResultStream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);

        /**
         * След като сетнем параметъра в заявката можем да получим резултата от нея
         * чрез: getResultStream(), getResultList(), getSingleResult(), getFirstResult и др.
         */
    }

    private void containsEmployee() throws IOException {
        System.out.println("Please enter employee's name");
        String[] employeeName = this.bufferedReader.readLine().split("\\s+");
        String firstName = employeeName[0];
        String lastName = employeeName[1];

        /**
         * Пример: JPQL - SELECT.
         * Може да бъде използван HQL, JPQL или дори чист SQL.
         * Възможно е в края на заявката да бъде посочено от какъв тип следва да бъде резултата.
         */
        Long singleResult = this.entityManager.createQuery(
                        "SELECT count(e) FROM Employee e " +
                                "WHERE e.firstName = :f_name AND e.lastName = :l_name", Long.class)
                .setParameter("f_name", firstName)
                .setParameter("l_name", lastName)
                .getSingleResult();

        System.out.println(singleResult == 0
                ? "No" : "Yes");
    }

    private void changeCasing() {
        /**
         * Необходимо е изпълнението на заявката да бъде в транзакция,
         * когато същата е от тип UPDATE or DELETE.
         */
        this.entityManager.getTransaction().begin();

        /**
         * Пример: JPQL - UPDATE.
         */
        Query query = this.entityManager.createQuery(
                "UPDATE Town t SET " +
                        "t.name = UPPER(t.name) " +
                        "WHERE LENGTH(t.name) >= 5");

        System.out.println(query.executeUpdate());

        this.entityManager.getTransaction().commit();
    }

    public Address createNewAddress(String newAddress) {
        Address address = new Address();
        address.setText(newAddress);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(address);
        this.entityManager.getTransaction().commit();

        return address;
    }

    private int removeAllAddressesRelatedToTheGivenTown(int townId) {
        List<Address> addresses = this.entityManager.createQuery(
                        "SELECT a FROM Address a " +
                                "WHERE a.town.id = :t_id", Address.class)
                .setParameter("t_id", townId)
                .getResultList();

        this.entityManager.getTransaction().begin();
        addresses.forEach(this.entityManager::remove);
        this.entityManager.getTransaction().commit();

        return addresses.size();
    }
}