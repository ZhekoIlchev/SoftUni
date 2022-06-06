import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Problem_06Ex_PrintAllMinionNames {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String NAME_DB = "minions_db";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();

        System.out.println("Enter user: ");
        String user = scanner.nextLine();

        System.out.println("Enter password");
        String password = scanner.nextLine().trim();

        properties.setProperty("user", user);
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection(CONNECTION_STRING + NAME_DB, properties);

        PreparedStatement preparedStatement = connection
                .prepareStatement(
                        "SELECT `name` " +
                        "FROM `minions`;");

        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> allMinions = new ArrayList<>();

        while (resultSet.next()) {
            allMinions.add(resultSet.getString("name"));
        }

        int startIndex = 0;
        int endIndex = allMinions.size() - 1;

        for (int i = 0; i < allMinions.size(); i++) {
            if (i % 2 == 0) {
                System.out.println(allMinions.get(startIndex++));
            } else {
                System.out.println(allMinions.get(endIndex--));
            }
        }
    }
}