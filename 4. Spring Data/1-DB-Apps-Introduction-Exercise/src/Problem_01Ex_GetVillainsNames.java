import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Problem_01Ex_GetVillainsNames {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String NAME_DB = "minions_db";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();

        System.out.println("Enter user:");
        String user = scanner.nextLine();

        System.out.println("Enter password");
        String password = scanner.nextLine();

        properties.setProperty("user", user);
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection(CONNECTION_STRING + NAME_DB, properties);

        PreparedStatement preparedStatement = connection
                .prepareStatement(
                        "SELECT v.`name`, COUNT(DISTINCT mv.`minion_id`) AS 'minions_count' " +
                                "FROM `villains` AS v " +
                                "JOIN `minions_villains` AS mv on v.`id` = mv.`villain_id` " +
                                "GROUP BY v.`name` " +
                                "HAVING `minions_count` > 15;");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString(1), resultSet.getInt(2));
        }
    }
}