import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab_Problem_05_ListManipulationAdvanced {
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
                case "Contains":
                    int number = Integer.parseInt(tokens[1]);

                    if (numbers.contains(number)) {
                        System.out.println("Yes");
                    } else {
                        System.out.println("No such number");
                    }
                    break;
                case "Print":
                    String type = tokens[1];
                    String numbersOfSameType = printNumbers(numbers, type);
                    System.out.println(numbersOfSameType);
                    break;
                case "Get":
                    int sum = 0;

                    for (Integer element : numbers) {
                        sum += element;
                    }
                    System.out.println(sum);
                    break;
                case "Filter":
                    String condition = tokens[1];
                    int conditionNumber = Integer.parseInt(tokens[2]);

                    if (condition.equals(">")) {
                        numbers.stream().filter(n -> conditionNumber < n).forEach(n -> System.out.print(n + " "));
                        System.out.println();
                    } else if (condition.equals(">=")) {
                        numbers.stream().filter(n -> conditionNumber <= n).forEach(n -> System.out.print(n + " "));
                        System.out.println();
                    } else if (condition.equals("<")) {
                        numbers.stream().filter(n -> conditionNumber > n).forEach(n -> System.out.print(n + " "));
                        System.out.println();
                    } else if (condition.equals("<=")) {
                        numbers.stream().filter(n -> conditionNumber >= n).forEach(n -> System.out.print(n + " "));
                        System.out.println();

                    }
                    break;
            }
        }
    }

    private static String printNumbers(List<Integer> numbers, String type) {
        StringBuilder sb = new StringBuilder();

        if (type.equals("odd")) {
            for (Integer number : numbers) {
                if (number % 2 != 0) {
                    sb.append(number).append(" ");
                }
            }
        } else if (type.equals("even")) {
            for (Integer number : numbers) {
                if (number % 2 == 0) {

                    sb.append(number).append(" ");
                }
            }
        }
        return sb.toString();
    }
}
