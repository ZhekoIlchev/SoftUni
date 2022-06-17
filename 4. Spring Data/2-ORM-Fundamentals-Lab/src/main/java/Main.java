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

//        User u = new User("Svetoslava", 26, LocalDate.now());
////        u.setId(2);
//        u.setUsername("Svetoslava_new_finalv2");
//
////        userEntityManager.doCreate(User.class);
////        userEntityManager.doAlter(User.class);
//        userEntityManager.persist(u);
//
////        User first = userEntityManager.findFirst(User.class);
////        User second = userEntityManager.findFirst(User.class, "id = 3");
////        System.out.println(first);
////        System.out.println(second);
//
////        Iterable<User> first = userEntityManager.find(User.class, "id < 5");
////        Iterable<User> users1 = userEntityManager.find(User.class);
////        System.out.println(users1.toString());
//
//        Iterable<User> first = userEntityManager.find(User.class);
//        System.out.println(first.toString());
//
//        User toDelete = userEntityManager.findFirst(User.class, "id = 4");
//        System.out.println(toDelete);
//
//        userEntityManager.delete(toDelete);
//
//        Iterable<User> second = userEntityManager.find(User.class);
//        System.out.println(second.toString());

    }
}