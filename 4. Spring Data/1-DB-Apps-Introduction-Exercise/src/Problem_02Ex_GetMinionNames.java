import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Problem_02Ex_GetMinionNames {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String NAME_DB = "minions_db";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();

        System.out.println("Enter user: ");
        String user = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine().trim();

        properties.setProperty("user", user);
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection(CONNECTION_STRING + NAME_DB, properties);

        System.out.println("Enter villain id:");
        int villainId = Integer.parseInt(scanner.nextLine());
        String villainName = getVillainName(connection, villainId);

        if (villainName == null) {
            System.out.printf("No villain with ID %d exists in the database. %n", villainId);
        } else {
            List<String> minions = allMinionsByVillainId(connection, villainId);
            System.out.printf("Villain: %s%n", villainName);

            int count = 0;
            for (String minion : minions) {
                System.out.printf("%d. %s%n", ++count, minion);
            }
        }
    }

    private static List<String> allMinionsByVillainId(Connection connection, int villainId) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(
                        "SELECT m.`name`, m.`age` " +
                                "FROM `minions` AS m " +
                                "JOIN `minions_villains` AS mv on m.`id` = mv.`minion_id` " +
                                "WHERE mv.`villain_id` = ?;");

        preparedStatement.setInt(1, villainId);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> minions = new ArrayList<>();
        while (resultSet.next()) {
            String minion = String.format("%s %d", resultSet.getString(1), resultSet.getInt(2));
            minions.add(minion);
        }

        return minions;
    }

    private static String getVillainName(Connection connection, int villainId) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement(
                        "SELECT `name` " +
                                "FROM `villains` " +
                                "WHERE `id` = ?;");

        preparedStatement.setInt(1, villainId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.isBeforeFirst()) {
            resultSet.next();
            return resultSet.getString(1);
        }

        return null;
    }
}