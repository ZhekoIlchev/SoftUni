import java.util.Arrays;
import java.util.Scanner;

public class Exercise_Problem_04_ArrayRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rotations = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < rotations; i++) {
            array = rotateArray(array);
        }

        for (int element : array) {
            System.out.print(element + " ");
        }
    }

    private static int[] rotateArray(int[] array) {

        int[] currentArray = new int[array.length];
        int firstElement = array[0];

        for (int i = 0; i < currentArray.length - 1; i++) {
            currentArray[i] = array[i + 1];
        }

        currentArray[currentArray.length - 1] = firstElement;
        return currentArray;
    }
}
