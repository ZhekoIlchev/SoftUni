import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Exercise_Problem_01_Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> wagons = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int maxCapacity = Integer.parseInt(scanner.nextLine());

        String command;
        while (!"end".equals(command = scanner.nextLine())) {
            String[] tokens = command.split("\\s+");

            if (tokens[0].equals("Add")) {
                int newWagon = Integer.parseInt(tokens[1]);
                wagons.add(newWagon);
            } else {
                int passengersToAdd = Integer.parseInt(tokens[0]);
                for (int i = 0; i < wagons.size(); i++) {
                    int currentPassengers = wagons.get(i);
                    if (currentPassengers + passengersToAdd <= maxCapacity) {
                        wagons.set(i, currentPassengers + passengersToAdd);
                        break;
                    }
                }
            }
        }

        for (Integer wagon : wagons) {
            System.out.print(wagon + " ");
        }
    }
}
