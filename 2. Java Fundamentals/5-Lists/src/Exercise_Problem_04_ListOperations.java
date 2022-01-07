import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Exercise_Problem_04_ListOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String command;
        while (!"End".equals(command = scanner.nextLine())) {
            String[] tokens = command.split("\\s+");

            switch (tokens[0]) {
                case "Add":
                    int numberToAdd = Integer.parseInt(tokens[1]);
                    numbers.add(numberToAdd);
                    break;
                case "Insert":
                    numberToAdd = Integer.parseInt(tokens[1]);
                    int indexToAdd = Integer.parseInt(tokens[2]);
                    if (isValid(numbers, indexToAdd)) {
                        numbers.add(indexToAdd, numberToAdd);
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                case "Remove":
                    int indexToRemove = Integer.parseInt(tokens[1]);
                    if (isValid(numbers, indexToRemove)) {
                        numbers.remove(indexToRemove);
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                case "Shift":
                    String direction = tokens[1];
                    int count = Integer.parseInt(tokens[2]);
                    shiftNumber(numbers, direction, count);
                    break;
            }
        }

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }
    }

    public static boolean isValid(List<Integer> list, int index) {
        return (0 <= index && index < list.size());
    }

    public static List<Integer> shiftNumber(List<Integer> list, String direction, int count) {
        if (direction.equals("left")) {
            for (int i = 0; i < count; i++) {

                int firstNumber = list.get(0);
                list.remove(0);
                list.add(firstNumber);
            }
        } else if (direction.equals("right")) {
            for (int i = 0; i < count; i++) {

                int lastNumber = list.get(list.size() - 1);
                list.remove(list.size() - 1);
                list.add(0, lastNumber);
            }
        }

        return list;
    }
}
