package Lab_Problem_04_FirstAndReserveTeam;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Team team = new Team("Black Eagles");

        for (int i = 0; i < n; i++) {
            String[] playerInfo = scanner.nextLine().split("\\s+");
            String firstName = playerInfo[0];
            String lastName = playerInfo[1];
            int age = Integer.parseInt(playerInfo[2]);
            double salary = Double.parseDouble(playerInfo[3]);

            try {
                Person person = new Person(firstName, lastName, age, salary);
                team.addPlayer(person);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        System.out.println("First team have " + team.getFirstTeam().size() + " players");
        System.out.println("Reserve team have " + team.getReserveTeam().size() + " players");
    }
}
