import java.util.Arrays;
import java.util.Scanner;

public class Lab_Problem_06_EqualArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] firstArray = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] secondArray = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        boolean isIdentical = true;
        int differenceCount = -1;

        if (firstArray.length == secondArray.length) {

            for (int i = 0; i < firstArray.length; i++) {
                if (firstArray[i] != secondArray[i]) {
                    isIdentical = false;
                    differenceCount = i;
                    break;
                }
            }

        } else {
            isIdentical = false;
        }

        if (isIdentical) {
            int sum = 0;

            for (int element : firstArray) {
                sum += element;
            }

            System.out.printf("Arrays are identical. Sum: %d", sum);
        } else {
            System.out.printf("Arrays are not identical. Found difference at %d index.", differenceCount);
        }
    }
}
