import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab_Problem_04_ListManipulationBasics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String command;

        while (!"end".equals(command = scanner.nextLine())) {
            String[] tokens = command.split("\\s+");

            String action = tokens[0];

            switch (action) {
                case "Add":
                    int addNumber = Integer.parseInt(tokens[1]);
                    numbers.add(addNumber);
                    break;
                case "Remove":
                    int removeNumber = Integer.parseInt(tokens[1]);
                    numbers.remove((Integer) removeNumber);
                    break;
                case "RemoveAt":
                    int removeAtIndex = Integer.parseInt(tokens[1]);
                    numbers.remove(removeAtIndex);
                    break;
                case "Insert":
                    int number = Integer.parseInt(tokens[1]);
                    int addToIndex = Integer.parseInt(tokens[2]);
                    numbers.add(addToIndex, number);
                    break;
            }
        }

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }
    }
}
