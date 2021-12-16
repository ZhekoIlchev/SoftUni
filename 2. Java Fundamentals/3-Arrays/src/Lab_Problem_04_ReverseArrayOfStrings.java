import java.util.Scanner;

public class Lab_Problem_04_ReverseArrayOfStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] array = scanner.nextLine().split("\\s+");

        for (int i = 0; i < array.length / 2; i++) {
            String swap = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = swap;
        }

        for (String element : array) {
            System.out.print(element + " ");
        }
    }
}
