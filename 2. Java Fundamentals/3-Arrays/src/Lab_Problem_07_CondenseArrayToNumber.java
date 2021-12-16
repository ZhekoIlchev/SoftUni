import java.util.Arrays;
import java.util.Scanner;

public class Lab_Problem_07_CondenseArrayToNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] condensedArray = new int[array.length - 1];
        int sum = 0;

        if (array.length == 1) {
            System.out.println(array[0]);
            return;
        }

        while (array.length != 2) {
            for (int i = 0; i <= array.length - 2; i++) {
                condensedArray[i] = array[i] + array[i + 1];
            }
            array = condensedArray;
            condensedArray = new int[array.length - 1];
        }

        for (int element : array) {
            sum += element;
        }
        System.out.println(sum);
    }
}
