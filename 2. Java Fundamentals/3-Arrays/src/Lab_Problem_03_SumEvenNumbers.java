import java.util.Arrays;
import java.util.Scanner;

public class Lab_Problem_03_SumEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int evenSum = 0;

        for (int number : numbers) {
            if (number % 2 == 0) {
                evenSum += number;
            }
        }

        System.out.println(evenSum);
    }
}
