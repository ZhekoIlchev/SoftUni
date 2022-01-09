import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab_Problem_05_LargestThreeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        numbers.stream()
                .sorted((a, b) -> Integer.compare(b, a))
                .limit(3)
                .forEach(n -> System.out.print(n + " "));
    }
}
