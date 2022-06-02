import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user:");
        String user = scanner.nextLine();
        user = user.equals("") ? "root" : user;

        System.out.println("Enter password:");
        String password = scanner.nextLine().trim();

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", properties);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT u.`first_name`, u.`last_name`, COUNT(*) AS 'games_count'\n" +
                        "FROM `users` AS u\n" +
                        "JOIN `users_games` AS ug\n" +
                        "ON u.`id` = ug.`user_id`\n" +
                        "JOIN `games` AS g\n" +
                        "ON ug.`game_id` = g.`id`\n" +
                        "WHERE `user_name` = ? \n" +
                        "GROUP BY `user_name`;");

        System.out.println("Enter user_name: ");
        String userName = scanner.nextLine();
        statement.setString(1, userName);

        ResultSet resultSet = statement.executeQuery();
        StringBuilder sb = new StringBuilder();

        if (resultSet.next()) {
            sb.append(String.format("User: %s", userName))
                    .append(System.lineSeparator())
                    .append(String.format("%s %s has played %d games",
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getInt("games_count")))
                    .append(System.lineSeparator());

            System.out.println(sb);
        } else {
            System.out.println("No such user exists");
        }
    }
}