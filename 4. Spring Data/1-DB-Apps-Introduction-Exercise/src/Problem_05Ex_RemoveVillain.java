import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Problem_05Ex_RemoveVillain {
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

        PreparedStatement findVillainById = connection
                .prepareStatement(
                        "SELECT * " +
                                "FROM minions_villains " +
                                "WHERE villain_id = ?;");

        System.out.println("Enter villain id: ");
        int villainId = Integer.parseInt(scanner.nextLine());

        findVillainById.setInt(1, villainId);

        ResultSet villainById = findVillainById.executeQuery();

        if (!villainById.isBeforeFirst()) {
            System.out.println("No such villain was found");
        } else {
            PreparedStatement releaseMinions = connection.prepareStatement(
                    "DELETE FROM minions_villains " +
                            "WHERE `villain_id` = ?;");

            releaseMinions.setInt(1, villainId);
            int affectedEntities = releaseMinions.executeUpdate();

            PreparedStatement findVillainNameById = connection
                    .prepareStatement(
                            "SELECT `name` " +
                                    "FROM villains " +
                                    "WHERE `id` = ?;");

            findVillainNameById.setInt(1, villainId);
            ResultSet villainNameById = findVillainNameById.executeQuery();

            villainNameById.next();
            String name = villainNameById.getString("name");

            PreparedStatement deleteVillainById = connection.prepareStatement(
                    "DELETE FROM `villains` " +
                            "WHERE `id` = ?;");

            deleteVillainById.setInt(1, villainId);
            deleteVillainById.executeUpdate();

            System.out.printf("%s was deleted%n", name);
            System.out.printf("%d minions released%n", affectedEntities);
        }
    }
}