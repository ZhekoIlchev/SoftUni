import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Exercise_Problem_02_ChangeList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String command;
        while (!"end".equals(command = scanner.nextLine())) {
            String[] tokens = command.split("\\s+");

            switch (tokens[0]) {
                case "Delete":
                    int element = Integer.parseInt(tokens[1]);
                    while (numbers.contains(element)) {
                        numbers.remove(Integer.valueOf(element));
                    }
                    break;
                case "Insert":
                    element = Integer.parseInt(tokens[1]);
                    int indexToInsert = Integer.parseInt(tokens[2]);
                    numbers.add(indexToInsert, element);
                    break;
            }
        }

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }
    }
}
