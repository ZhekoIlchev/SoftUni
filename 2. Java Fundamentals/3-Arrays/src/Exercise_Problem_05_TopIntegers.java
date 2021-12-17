import java.util.Arrays;
import java.util.Scanner;

public class Exercise_Problem_05_TopIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < array.length - 1; i++) {

            boolean isTopInteger = true;

            for (int j = i + 1; j < array.length; j++) {

                if (array[i] <= array[j]) {
                    isTopInteger = false;
                    break;
                }
            }

            if (isTopInteger) {
                System.out.print(array[i] + " ");
            }
        }

        System.out.println(array[array.length - 1]);
    }
}
