import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your username.");
        String user = scanner.nextLine();

        System.out.println("Please enter your password.");
        String password = scanner.nextLine();

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", properties);

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `employees` WHERE `salary` > ?");

        System.out.println("Please enter salary.");
        String salary = scanner.nextLine();
        statement.setDouble(1, Double.parseDouble(salary));

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("Employee name: %s %s%n",
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"));
        }
    }
}