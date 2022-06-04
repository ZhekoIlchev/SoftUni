import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class Problem_03Ex_AddMinion {
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

        String[] minionInformation = scanner.nextLine().split(": ");
        String[] currentMinion = minionInformation[1].split("\\s+");
        String minionName = currentMinion[0];
        int minionAge = Integer.parseInt(currentMinion[1]);
        String minionTown = currentMinion[2];

        String[] villainInformation = scanner.nextLine().split(": ");
        String villainName = villainInformation[1];

        PreparedStatement statementTown = connection
                .prepareStatement(
                        "SELECT `id`, `name` " +
                                "FROM `towns` " +
                                "WHERE `name` = ?;");

        statementTown.setString(1, minionTown);

        ResultSet rsTown = statementTown.executeQuery();

        if (!rsTown.isBeforeFirst()) {
            PreparedStatement statementAddTown = connection
                    .prepareStatement(
                            "INSERT INTO `towns`(`name`, `country`) " +
                                    "VALUES " +
                                    "(?, ?);");

            statementAddTown.setString(1, minionTown);
            statementAddTown.setString(2, "someCountry");
            statementAddTown.execute();

            System.out.printf("Town %s was added to the database.%n", minionTown);
        }

        PreparedStatement findTownId = connection
                .prepareStatement(
                        "SELECT `id`, `name` " +
                                "FROM `towns` " +
                                "WHERE `name` = ?;");

        findTownId.setString(1, minionTown);

        ResultSet rsFindTownId = findTownId.executeQuery();
        int townId = 0;
        if (rsFindTownId.next()) {
            townId = rsFindTownId.getInt(1);
        }

        PreparedStatement statementVillain = connection
                .prepareStatement(
                        "SELECT `id`, `name` " +
                                "FROM `villains` " +
                                "WHERE `name` = ?;");

        statementVillain.setString(1, villainName);

        ResultSet rsVillain = statementVillain.executeQuery();

        if (!rsVillain.isBeforeFirst()) {
            PreparedStatement statementAddVillain = connection
                    .prepareStatement(
                            "INSERT INTO `villains`(`name`, `evilness_factor`) " +
                                    "VALUES " +
                                    "(?, ?);");

            statementAddVillain.setString(1, villainName);
            statementAddVillain.setString(2, "evil");
            statementAddVillain.execute();

            System.out.printf("Villain %s was added to the database.%n", villainName);
        }

        PreparedStatement findVillainId = connection
                .prepareStatement(
                        "SELECT `id`, `name` " +
                                "FROM `villains` " +
                                "WHERE `name` = ?;");

        findVillainId.setString(1, villainName);

        ResultSet rsFindVillainId = findVillainId.executeQuery();
        int villainId = 0;
        if (rsFindVillainId.next()) {
            villainId = rsFindVillainId.getInt(1);
        }

        PreparedStatement addMinionToMinionsTable = connection.prepareStatement(
                "INSERT INTO `minions`(`name`, `age`, `town_id`) " +
                        "VALUES " +
                        "(?, ?, ?);");

        addMinionToMinionsTable.setString(1, minionName);
        addMinionToMinionsTable.setInt(2, minionAge);
        addMinionToMinionsTable.setInt(3, townId);
        addMinionToMinionsTable.execute();

        PreparedStatement findMinionIdByName = connection.prepareStatement(
                "SELECT `id`, `name` " +
                        "FROM `minions` " +
                        "WHERE `name` = ?;");

        findMinionIdByName.setString(1, minionName);

        ResultSet rsMinionId = findMinionIdByName.executeQuery();
        int minionId = 0;
        if (rsMinionId.next()) {
            minionId = rsMinionId.getInt(1);
        }

        PreparedStatement addMinionAndVillain = connection
                .prepareStatement(
                        "INSERT INTO `minions_villains`(`minion_id`, `villain_id`) " +
                                "VALUES " +
                                "(?, ?);");

        addMinionAndVillain.setInt(1, minionId);
        addMinionAndVillain.setInt(2, villainId);
        addMinionAndVillain.execute();

        System.out.printf("Successfully added %s to be minion of %s%n", minionName, villainName);
    }
}