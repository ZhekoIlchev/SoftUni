import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Exercise_Problem_06_EqualSums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();


        if (array.length == 1) {
            System.out.println(0);
        } else if ((array.length == 2 && array[1] == 0)) {
            System.out.println(0);
        } else if (array.length == 2 && array[0] == 0) {
            System.out.println(1);
        } else {
            boolean isExist = false;

            for (int i = 0; i < array.length; i++) {

                int leftSum = 0;
                int rightSum = 0;
                leftSum = calculateLeftSum(array, i, leftSum);
                rightSum = calculateRightSum(array, i, rightSum);

                if (leftSum == rightSum) {
                    isExist = true;
                    System.out.println(i);
                }
            }

            if (!isExist) {
                System.out.println("no");
            }
        }
    }

    private static int calculateRightSum(int[] array, int i, int rightSum) {
        for (int j = i + 1; j < array.length; j++) {

            rightSum += array[j];
        }

        return rightSum;
    }

    private static int calculateLeftSum(int[] array, int i, int leftSum) {
        for (int j = 0; j < i; j++) {
            leftSum += array[j];
        }

        return leftSum;
    }
}
