package exercise;

import java.util.Scanner;

public class StrongNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int number = Integer.parseInt(input);
        int sum = 0;

        if (number == 0) {
            System.out.println("no");
            return;
        }

        while (number > 0) {
            int lastNumber = number % 10;
            int result = 1;

            for (int i = lastNumber; i > 0; i--) {
                result *= i;
            }

            sum += result;
            number /= 10;
        }

        if (sum == Integer.parseInt(input)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}