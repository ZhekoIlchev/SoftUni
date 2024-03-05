import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Connection connection;

    public static void main(String[] args) throws IOException, SQLException {

        connection = getConnection();

        System.out.println("Enter exercise number.");
        int exerciseNumber = Integer.parseInt(reader.readLine());

        switch (exerciseNumber) {
            case 2:
                exerciseTwo();
                break;
            case 3:
                exerciseThree();
                break;
            case 4:
                exerciseFour();
            case 5:
                exerciseFive();
            case 6:
                exerciseSix();
            case 7:
                exerciseSeven();
            case 8:
                exerciseEight();
            case 9:
                exerciseNine();
        }
    }

    private static void exerciseTwo() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT v.name, COUNT(DISTINCT mv.minion_id) AS `m_count` FROM `villains` AS v " +
                        "JOIN `minions_villains` AS `mv` ON v.`id` = mv.`villain_id`" +
                        "GROUP BY v.name " +
                        "HAVING m_count > 15");

        ResultSet resultSet = preparedStatement.executeQuery();
        StringBuilder stringBuilder = new StringBuilder();

        while (resultSet.next()) {
            stringBuilder.append(String.format("%s %s", resultSet.getString("v.name"), resultSet.getInt("m_count")))
                    .append(System.lineSeparator());
        }

        System.out.println(stringBuilder.toString().trim());
    }

    private static void exerciseThree() throws SQLException, IOException {
        System.out.println("Please enter villain ID.");
        int villainId = Integer.parseInt(reader.readLine());

        String villainName = findEntityById("villains", villainId);
        Map<String, Integer> minions = findMinionsByVillainId(villainId);
        int counter = 1;

        System.out.println(villainName);
        for (Map.Entry<String, Integer> entity : minions.entrySet()) {
            System.out.printf(String.format("%d. %s %d%n", counter++, entity.getKey(), entity.getValue()));
        }
    }

    private static void exerciseFour() throws IOException, SQLException {
        System.out.println("Enter minion info.");
        String[] minionInfo = reader.readLine().split("\\s+");
        System.out.println("Enter villain info.");
        String[] villainInfo = reader.readLine().split("\\s+");

        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String town = minionInfo[3];

        String villainName = villainInfo[1];

        if (!findIfTownExists(town)) {
            addTown(town);
        }

        int town_id = getTownIdByName(town);
        addMinion(minionName, minionAge, town_id);
        int minion_id = getMinionIdByName(minionName);

        if (!findIfVillainExists(villainName)) {
            addVillain(villainName);
        }

        int villain_id = getVillainIdByName(villainName);

        if (addMinionToVillain(minion_id, villain_id) != 0) {
            System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);
        } else {
            System.out.println("There is a problem with current query!");
        }
    }

    private static void exerciseFive() throws SQLException, IOException {
        System.out.println("Enter country.");
        String country = reader.readLine();

        PreparedStatement townsToUpdate = connection.prepareStatement(
                "UPDATE `towns` " +
                        "SET `name` = UPPER(`name`) " +
                        "WHERE `country` = ?;");

        townsToUpdate.setString(1, country);

        int affectedRows = townsToUpdate.executeUpdate();

        if (affectedRows == 0) {
            System.out.println("No town names were affected.");
            return;
        }

        PreparedStatement updatedTowns = connection.prepareStatement(
                "SELECT `name` " +
                        "FROM `towns` " +
                        "WHERE `country` = ?;");

        updatedTowns.setString(1, country);
        ResultSet resultSet = updatedTowns.executeQuery();
        List<String> towns = new ArrayList<>();

        while (resultSet.next()) {
            towns.add(resultSet.getString("name"));
        }

        System.out.printf("%d town names were affected.%n", affectedRows);
        System.out.printf("[%s]", String.join(", ", towns));
    }

    private static void exerciseSix() throws IOException, SQLException {
        System.out.println("Enter villain ID.");
        int villainId = Integer.parseInt(reader.readLine());

        int deletedMinions = deleteVillainAndItsMinions(villainId);
        deleteVillainFromVillainsTable(villainId);

        if (deletedMinions != 0) {
            System.out.printf("%s minions released.%n", deletedMinions);
        }
    }

    private static void exerciseSeven() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT `name` " +
                        "FROM `minions` ");

        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> allMinions = new ArrayList<>();

        while (resultSet.next()) {
            allMinions.add(resultSet.getString("name"));
        }
        int startIndex = 0;
        int lastIndex = allMinions.size() - 1;

        for (int i = 0; i < allMinions.size(); i++) {

            System.out.println(i % 2 == 0
                    ? allMinions.get(startIndex++)
                    : allMinions.get(lastIndex--));
        }
    }

    private static void exerciseEight() throws IOException, SQLException {
        System.out.println("Enter minions IDs");

        Set<Integer> minionIds = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toSet());

        String inClause = convertSetToParameters(minionIds);
        String increaseAgeQuery = String.format(
                "UPDATE `minions` " +
                        "SET `age` = `age` + 1 " +
                        "WHERE `id` IN (%s);", inClause);

        String toLowerCaseNameQuery = String.format(
                "UPDATE `minions` " +
                        "SET `name` = LOWER(`name`) " +
                        "WHERE `id` IN(%s);", inClause);

        PreparedStatement preparedStatementAge = connection.prepareStatement(increaseAgeQuery);
        setValuesToInClause(preparedStatementAge, minionIds);
        PreparedStatement preparedStatementName = connection.prepareStatement(toLowerCaseNameQuery);
        setValuesToInClause(preparedStatementName, minionIds);

        preparedStatementAge.executeUpdate();
        preparedStatementName.executeUpdate();

        String selectMinionsQuery = String.format(
                "SELECT `name`, `age` " +
                        "FROM `minions`;");

        PreparedStatement preparedStatementMinions = connection.prepareStatement(selectMinionsQuery);

        ResultSet resultSet = preparedStatementMinions.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d%n", resultSet.getString("name"), resultSet.getInt("age"));
        }
    }

    private static void exerciseNine() throws IOException, SQLException {
        System.out.println("Enter minion id.");
        int minionId = Integer.parseInt(reader.readLine());

        /**
         * We have to create our stored procedure:
         * CREATE PROCEDURE usp_get_older (minion_id INT)
         * BEGIN
         * 	UPDATE `minions`
         *     SET `age` = `age` + 1
         *     WHERE `id` = minion_id;
         * END &&
         * DELIMITER ;
         */

        CallableStatement callableStatement = connection.prepareCall(
                "CALL usp_get_older(?);");
        callableStatement.setInt(1, minionId);
        callableStatement.executeUpdate();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT `name`, `age` " +
                        "FROM `minions` " +
                        "WHERE `id` = ?;");
        preparedStatement.setInt(1, minionId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString("name"), resultSet.getInt("age"));
        }
    }

    private static String findEntityById(String tableName, int entityId) throws SQLException {
        String query = String.format("SELECT `name` FROM %s WHERE `id` = ?;", tableName);

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, entityId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.isBeforeFirst()) {
            resultSet.next();
            return String.format("Villain: %s", resultSet.getString("name"));
        } else {
            return "There is no villain with the given id.";
        }
    }

    private static HashMap<String, Integer> findMinionsByVillainId(int villainId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT m.`name`, m.`age` " +
                        "FROM `minions` AS m " +
                        "JOIN `minions_villains` AS mv " +
                        "ON m.`id` = mv.`minion_id` " +
                        "WHERE `villain_id` = ?;");

        preparedStatement.setInt(1, villainId);

        ResultSet resultSet = preparedStatement.executeQuery();
        HashMap<String, Integer> result = new LinkedHashMap<>();

        while (resultSet.next()) {
            result.putIfAbsent(resultSet.getString("name"), resultSet.getInt("age"));
        }

        return result;
    }

    private static boolean findIfTownExists(String town) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT `name` " +
                        "FROM `towns` " +
                        "WHERE `name` = ?;");

        preparedStatement.setString(1, town);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.isBeforeFirst();
    }

    private static void addTown(String town) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO `towns`(`name`) " +
                        "VALUES " +
                        "(?);");

        preparedStatement.setString(1, town);

        preparedStatement.execute();
        System.out.printf("Town %s was added to the database.%n", town);
    }

    private static int getTownIdByName(String town) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT `id` " +
                        "FROM `towns` " +
                        "WHERE `name` = ?;");

        preparedStatement.setString(1, town);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("id");
    }

    private static void addMinion(String minionName, int minionAge, int town_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO `minions`(`name`, `age`, `town_id`) " +
                        "VALUES " +
                        "(?, ?, ?);");

        preparedStatement.setString(1, minionName);
        preparedStatement.setInt(2, minionAge);
        preparedStatement.setInt(3, town_id);

        preparedStatement.execute();
    }

    private static int getMinionIdByName(String minionName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT `id` " +
                        "FROM `minions` " +
                        "WHERE `name` = ?;");

        preparedStatement.setString(1, minionName);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("id");
    }

    private static boolean findIfVillainExists(String villainName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT `name` " +
                        "FROM `villains` " +
                        "WHERE `name` = ?");

        preparedStatement.setString(1, villainName);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.isBeforeFirst();
    }

    private static void addVillain(String villainName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO `villains`(`name`, `evilness_factor`) " +
                        "VALUES " +
                        "(?, 'evil');");

        preparedStatement.setString(1, villainName);

        preparedStatement.execute();
        System.out.printf("Villain %s was added to the database.%n", villainName);
    }

    private static int getVillainIdByName(String villainName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT `id` " +
                        "FROM `villains` " +
                        "WHERE `name` = ?");

        preparedStatement.setString(1, villainName);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("id");
    }

    private static int addMinionToVillain(int minion_id, int villain_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO `minions_villains`(`minion_id`, `villain_id`) " +
                        "VALUES " +
                        "(?, ?);");

        preparedStatement.setInt(1, minion_id);
        preparedStatement.setInt(2, villain_id);

        return preparedStatement.executeUpdate();
    }

    private static int deleteVillainAndItsMinions(int villainId) throws SQLException {
        PreparedStatement minionsAndVillainsToDelete = connection.prepareStatement(
                "DELETE FROM `minions_villains` " +
                        "WHERE `villain_id` = ?;");

        minionsAndVillainsToDelete.setInt(1, villainId);
        return minionsAndVillainsToDelete.executeUpdate();
    }

    private static void setValuesToInClause(PreparedStatement preparedStatement, Set<Integer> ids) throws SQLException {
        int counter = 1;
        for (Integer id : ids) {
            preparedStatement.setInt(counter++, id);
        }
    }

    private static void deleteVillainFromVillainsTable(int villainId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT `name` " +
                        "FROM `villains` " +
                        "WHERE `id` = ?;");

        preparedStatement.setInt(1, villainId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.isBeforeFirst()) {
            System.out.println("No such villain was found.");
            return;
        }

        resultSet.next();
        String villainName = resultSet.getString("name");

        PreparedStatement deleteVillain = connection.prepareStatement(
                "DELETE FROM `villains` " +
                        "WHERE `id` = ?");

        deleteVillain.setInt(1, villainId);

        if (deleteVillain.executeUpdate() != 0) {
            System.out.printf("%s was deleted.%n", villainName);
        }
    }

    private static String convertSetToParameters(Set<Integer> minionIds) {
        return minionIds
                .stream()
                .map(e -> "?")
                .collect(Collectors.joining(", "));
    }

    private static Connection getConnection() throws IOException, SQLException {
        System.out.println("Please enter user.");
        String user = reader.readLine();

        System.out.println("Please enter password.");
        String password = reader.readLine();

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        return DriverManager.getConnection(CONNECTION_STRING + DATABASE_NAME, properties);
    }
}