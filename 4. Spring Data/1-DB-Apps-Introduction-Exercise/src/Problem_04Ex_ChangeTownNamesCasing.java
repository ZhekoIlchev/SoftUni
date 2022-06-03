import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Problem_04Ex_ChangeTownNamesCasing {
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

        PreparedStatement preparedStatement = connection
                .prepareStatement(
                        "UPDATE `towns` " +
                                "SET `name` = UPPER(`name`) " +
                                "WHERE `country` = ?;");

        System.out.println("Enter country: ");
        String country = scanner.nextLine();

        preparedStatement.setString(1, country);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            System.out.println("No town names were affected.");
            return;
        }

        PreparedStatement preparedStatementTowns = connection.prepareStatement(
                "SELECT `name` " +
                        "FROM `towns` " +
                        "WHERE `country` = ?;");

        preparedStatementTowns.setString(1, country);

        ResultSet resultSet = preparedStatementTowns.executeQuery();

        List<String> towns = new ArrayList<>();
        while (resultSet.next()) {
            String town = resultSet.getString(1);
            towns.add(town);
        }

        StringBuilder sb = new StringBuilder(String.format("%d town names were affected.", affectedRows));

        sb.append(System.lineSeparator())
                .append("[")
                .append(String.join(", ", towns))
                .append("]");

        System.out.println(sb);
    }
}