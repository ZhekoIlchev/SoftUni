import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab_Problem_07_RemoveNegativesAndReverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int index = 0;
        while (index < numbers.size()) {
            int currentElement = numbers.get(index);

            if (currentElement < 0) {
                numbers.remove(index);
            } else {
                index++;
            }
        }

        if (numbers.isEmpty()) {
            System.out.println("empty");
        } else {

            Collections.reverse(numbers);
            for (Integer number : numbers) {
                System.out.print(number + " ");
            }
        }
    }
}
