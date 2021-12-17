import java.util.Arrays;
import java.util.Scanner;

public class Exercise_Problem_03_ZigZagArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] arrA = new int[n];
        int[] arrB = new int[n];


        for (int i = 0; i < n; i++) {
            int[] currentArray = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (i % 2 == 0) {
                arrA[i] = currentArray[0];
                arrB[i] = currentArray[1];
            } else {
                arrA[i] = currentArray[1];
                arrB[i] = currentArray[0];
            }
        }

        printArray(arrA);
        printArray(arrB);
    }

    public static void printArray(int[] array) {

        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
