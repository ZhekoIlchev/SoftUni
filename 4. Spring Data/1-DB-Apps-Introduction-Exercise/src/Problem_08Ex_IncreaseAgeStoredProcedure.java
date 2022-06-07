import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Problem_08Ex_IncreaseAgeStoredProcedure {
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

        CallableStatement increaseMinionAgeByOne = connection.prepareCall(
                "CALL usp_get_older(?);");

        System.out.println("Enter minion id: ");
        int minionId = Integer.parseInt(scanner.nextLine());
        increaseMinionAgeByOne.setInt(1, minionId);
        increaseMinionAgeByOne.executeUpdate();

        PreparedStatement minionInformation = connection.prepareStatement(
                "SELECT `name`, `age` " +
                        "FROM `minions` " +
                        "WHERE `id` = ?;");

        minionInformation.setInt(1, minionId);

        ResultSet resultSet = minionInformation.executeQuery();
        if (resultSet.next()) {
            System.out.printf("%s %d%n",
                    resultSet.getString("name"),
                    resultSet.getInt("age"));
        }
    }
}