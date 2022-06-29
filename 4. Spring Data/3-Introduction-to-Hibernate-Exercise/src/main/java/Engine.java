import entities.Employee;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.entityManager.close();
        }

    }

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

        if(result != 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }

    private void exerciseTwo() {
        this.entityManager.getTransaction()
                .begin();

        int affectedEntities = entityManager.createQuery(
                        "UPDATE Town t " +
                                "SET t.name = upper(t.name) " +
                                "WHERE length(t.name) <= 5")
                .executeUpdate();

        System.out.println(affectedEntities);

        entityManager.getTransaction()
                .commit();
    }
}