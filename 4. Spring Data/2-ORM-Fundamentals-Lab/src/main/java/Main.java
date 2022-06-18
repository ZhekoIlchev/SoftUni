import entities.Address;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user: ");
        String user = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        MyConnector.createConnection(user, password, "custom-orm");
        Connection connection = MyConnector.getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        EntityManager<Address> addressEntityManager = new EntityManager<>(connection);

//        1. Create table Addresses in Database.
        addressEntityManager.doCreate(Address.class);
    }
}