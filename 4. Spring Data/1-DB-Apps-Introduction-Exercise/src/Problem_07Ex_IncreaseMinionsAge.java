import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class Problem_07Ex_IncreaseMinionsAge {
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

        System.out.println("Enter 3 IDs:");
        int[] minionsId = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int firstId = minionsId[0];
        int secondId = minionsId[1];
        int thirdId = minionsId[2];

        PreparedStatement increaseMinionsAge = connection.prepareStatement(
                "UPDATE `minions` " +
                        "SET `age` = `age` + 1 " +
                        "WHERE `id` IN (?, ?, ?);");

        increaseMinionsAge.setInt(1, firstId);
        increaseMinionsAge.setInt(2, secondId);
        increaseMinionsAge.setInt(3, thirdId);
        increaseMinionsAge.executeUpdate();

        PreparedStatement minionNamesToLowerCase = connection.prepareStatement(
                "UPDATE `minions` " +
                        "SET `name` = LOWER(`name`) " +
                        "WHERE `id` IN (?, ?, ?);");

        minionNamesToLowerCase.setInt(1, firstId);
        minionNamesToLowerCase.setInt(2, secondId);
        minionNamesToLowerCase.setInt(3, thirdId);
        minionNamesToLowerCase.executeUpdate();

        PreparedStatement allMinions = connection.prepareStatement(
                "SELECT `name`, `age` " +
                        "FROM `minions`;");

        ResultSet resultSet = allMinions.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d%n", resultSet.getString(1), resultSet.getInt("age"));
        }
    }
}