import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;
import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        System.out.println("Please enter exercise number.");

        try {
            int exerciseNumber = Integer.parseInt(this.bufferedReader.readLine());

            switch (exerciseNumber) {
                case 2 -> exerciseTwo();
                case 3 -> exerciseThree();
                case 4 -> exerciseFour();
                case 5 -> exerciseFive();
                case 6 -> exerciseSix();
                case 7 -> exerciseSeven();
                case 8 -> exerciseEight();
                case 9 -> exerciseNine();
                case 10 -> exerciseTen();
                case 11 -> exerciseEleven();
                case 12 -> exerciseTwelve();
                case 13 -> exerciseThirteen();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.entityManager.close();
        }

    }

    /**
     * Problem 13. Remove Towns
     */
    private void exerciseThirteen() throws IOException {
        System.out.println("Please enter town name");
        String townName = this.bufferedReader.readLine();

        Town town = this.entityManager.createQuery(
                        "SELECT t " +
                                "FROM Town t " +
                                "WHERE t.name = :t_name", Town.class)
                .setParameter("t_name", townName)
                .getSingleResult();

        int deletedAddresses = deleteAddressByTownId(town.getId());

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(town);
        this.entityManager.getTransaction().commit();

        System.out.printf("%d address in %s deleted",
                deletedAddresses, townName);
    }

    private int deleteAddressByTownId(Integer id) {
        List<Address> addresses = this.entityManager.createQuery(
                        "SELECT a " +
                                "FROM Address a " +
                                "WHERE a.town.id = :t_id", Address.class)
                .setParameter("t_id", id)
                .getResultList();

        this.entityManager.getTransaction().begin();
        addresses.forEach(this.entityManager::remove);
        this.entityManager.getTransaction().commit();

        return addresses.size();
    }

    /**
     * Problem 12. Employees Maximum Salaries
     */
    @SuppressWarnings("unchecked")
    private void exerciseTwelve() {
        List<Object[]> resultList = this.entityManager.createNativeQuery(
                        "SELECT d.`name`, MAX(e.`salary`) AS 'max_salary' " +
                                "FROM departments AS d " +
                                "JOIN employees e on d.`department_id` = e.`department_id` " +
                                "GROUP BY d.`department_id` " +
                                "HAVING `max_salary` NOT BETWEEN 30000 AND 70000;")
                .getResultList();

        for (Object[] objects : resultList) {
            System.out.printf("%s %.2f%n",
                    objects[0], (BigDecimal) objects[1]);
        }
    }

    /**
     * Problem 11. Find Employees by First Name
     */
    private void exerciseEleven() throws IOException {
        System.out.println("Please enter pattern");
        String startString = this.bufferedReader.readLine();
        String pattern = startString + "%";

        this.entityManager.createQuery(
                        "SELECT e " +
                                "FROM Employee e " +
                                "WHERE e.firstName LIKE :p_like", Employee.class)
                .setParameter("p_like", pattern)
                .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %s - %s - ($%.2f)%n",
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getJobTitle(),
                            employee.getSalary());
                });

    }

    /**
     * Problem 10. Increase Salaries
     */
    private void exerciseTen() {
        this.entityManager.getTransaction().begin();

        int affectedRows = this.entityManager.createQuery(
                        "UPDATE Employee e " +
                                "SET e.salary = e.salary * 1.12 " +
                                "WHERE e.department.id IN :ids")
                .setParameter("ids", Set.of(1, 2, 4, 11))
                .executeUpdate();

        this.entityManager.getTransaction().commit();
        System.out.println(affectedRows);

        this.entityManager.createQuery(
                        "SELECT e FROM Employee e " +
                                "WHERE e.department.id IN :ids", Employee.class)
                .setParameter("ids", Set.of(1, 2, 4, 11))
                .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %s ($%.2f)%n",
                            employee.getFirstName(), employee.getLastName(), employee.getSalary());
                });
    }

    /**
     * Problem 9. Find Latest 10 Projects
     */
    private void exerciseNine() {
        this.entityManager.createQuery(
                        "SELECT p " +
                                "FROM Project p " +
                                "ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(project -> {
                    LocalDateTime currentStartDate = project.getStartDate();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = currentStartDate.format(dateTimeFormatter);

                    StringBuilder output = new StringBuilder();
                    output.append(String.format("Project name: %s", project.getName()))
                            .append(System.lineSeparator())
                            .append(String.format("    Project Description: %s", project.getDescription()))
                            .append(System.lineSeparator())
                            .append(String.format("    Project Start Date:%s", formattedDateTime))
                            .append(System.lineSeparator())
                            .append(String.format("    Project End Date: %s", project.getEndDate() == null ? "null" : project.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

                    System.out.println(output);
                });
    }

    /**
     * Problem 8. Get Employee with Project
     */
    private void exerciseEight() throws IOException {
        System.out.println("Please enter id");
        int id = Integer.parseInt(this.bufferedReader.readLine());

        Employee employee = this.entityManager.find(Employee.class, id);
        System.out.printf("%s %s - %s%n",
                employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        employee.getProjects()
                .stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(project -> System.out.println(project.getName()));
    }

    /**
     * Problem 7. Addresses with Employee Count
     */
    private void exerciseSeven() {
        this.entityManager.createQuery(
                        "SELECT a " +
                                "FROM Address a " +
                                "ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(address -> {
                    System.out.printf("%s, %s - %d employees%n",
                            address.getText(),
                            address.getTown() == null ? "Unknown" : address.getTown().getName(),
                            address.getEmployees().size());
                });
    }

    /**
     * Problem 6. Adding a New Address and Updating Employee
     */
    private void exerciseSix() throws IOException {
        System.out.println("Please enter employee last name");
        String lastName = this.bufferedReader.readLine();

        Employee employee = this.entityManager.createQuery(
                        "SELECT e " +
                                "FROM Employee e " +
                                "WHERE e.lastName = :l_name", Employee.class)
                .setParameter("l_name", lastName)
                .getSingleResult();

        Address address = createAddress("Vitoshka 15");
        employee.setAddress(address);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(employee);
        this.entityManager.getTransaction().commit();
    }

    private Address createAddress(String addressText) {
        Address address = new Address();
        address.setText(addressText);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(address);
        this.entityManager.getTransaction().commit();

        return address;
    }

    /**
     * Problem 5. Employees from Department
     */
    private void exerciseFive() {
        this.entityManager.createQuery(
                        "SELECT e " +
                                "FROM Employee e " +
                                "WHERE e.department.name = :d_name " +
                                "ORDER BY e.salary ASC, e.id ASC", Employee.class)
                .setParameter("d_name", "Research and Development")
                .getResultStream()
                .forEach(employee -> System.out.printf("%s %s from Research and Development - $%.2f%n",
                        employee.getFirstName(), employee.getLastName(), employee.getSalary()));
    }

    /**
     * Problem 4. Employees with Salary Over 50 000
     */
    private void exerciseFour() throws IOException {
        this.entityManager.createQuery(
                        "SELECT e " +
                                "FROM Employee e " +
                                "WHERE e.salary > :min_salary", Employee.class)
                .setParameter("min_salary", BigDecimal.valueOf(50000L))
                .getResultStream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }

    /**
     * Problem 3. Contains Employee
     */
    private void exerciseThree() throws IOException {
        System.out.println("Please enter employee full name.");
        String[] fullName = this.bufferedReader.readLine().split("\\s+");
        String firstName = fullName[0];
        String lastName = fullName[1];

        Long result = this.entityManager.createQuery(
                        "SELECT count (e.id) " +
                                "FROM Employee e " +
                                "WHERE e.firstName = :f_name AND e.lastName = :l_name", Long.class)
                .setParameter("f_name", firstName)
                .setParameter("l_name", lastName)
                .getSingleResult();

        if (result != 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    /**
     * Problem 2. Change casing
     */
    private void exerciseTwo() {
        this.entityManager.getTransaction().begin();

        int affectedEntities = entityManager.createQuery(
                        "UPDATE Town t " +
                                "SET t.name = upper(t.name) " +
                                "WHERE length(t.name) <= 5")
                .executeUpdate();

        System.out.println(affectedEntities);

        entityManager.getTransaction().commit();
    }
}