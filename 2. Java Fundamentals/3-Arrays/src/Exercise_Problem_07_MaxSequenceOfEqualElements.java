import java.util.Arrays;
import java.util.Scanner;

public class Exercise_Problem_07_MaxSequenceOfEqualElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int max = 1;
        int element = 0;

        for (int i = 0; i < array.length; i++) {

            int currentMax = 1;
            int currentElement;

            for (int j = i + 1; j < array.length; j++) {

                if (array[i] == array[j]) {
                    currentMax++;
                    currentElement = array[i];
                } else {
                    break;
                }

                if (currentMax > max) {
                    max = currentMax;
                    element = currentElement;
                }
            }
        }

        for (int i = 0; i < max; i++) {
            System.out.print(element + " ");
        }
    }
}
