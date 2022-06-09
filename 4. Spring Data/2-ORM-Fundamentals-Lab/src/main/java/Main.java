import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user: ");
        String user = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        MyConnector.createConnection(user, password, "custom-orm");
        Connection connection = MyConnector.getConnection();

        EntityManager<User> entityManager = new EntityManager<>(connection);

        User u = new User("Svetoslava", 26, LocalDate.now());

//        entityManager.doCreate(User.class);
        entityManager.persist(u);
    }
}