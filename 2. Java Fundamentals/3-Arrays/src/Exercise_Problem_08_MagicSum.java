import java.util.Arrays;
import java.util.Scanner;

public class Exercise_Problem_08_MagicSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int magicSum = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < array.length; i++) {

            for (int j = i + 1; j < array.length; j++) {

                if (array[i] + array[j] == magicSum) {

                    String elements = String.format("%d %d", array[i], array[j]);
                    System.out.println(elements);
                }
            }
        }
    }
}
