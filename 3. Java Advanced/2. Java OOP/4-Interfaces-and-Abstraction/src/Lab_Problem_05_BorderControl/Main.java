package Lab_Problem_05_BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Identifiable> list = new ArrayList<>();
        String command;

        while (!"End".equals(command = scanner.nextLine())) {
            String[] tokens = command.split("\\s+");

            Identifiable identifiable = null;

            if (tokens.length == 3) {
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                String citizenId = tokens[2];
                identifiable = new Citizen(name, age, citizenId);
            } else if (tokens.length == 2) {
                String model = tokens[0];
                String robotId = tokens[1];
                identifiable = new Robot(model, robotId);
            }

            list.add(identifiable);
        }

        String fakeIdPostfix = scanner.nextLine();
        for (Identifiable identifiable : list) {
            if (identifiable.getId().endsWith(fakeIdPostfix)) {
                System.out.println(identifiable.getId());
            }
        }
    }
}
