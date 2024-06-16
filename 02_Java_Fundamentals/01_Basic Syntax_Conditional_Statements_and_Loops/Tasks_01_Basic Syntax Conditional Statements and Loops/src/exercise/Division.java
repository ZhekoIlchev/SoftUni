package exercise;

import java.util.Scanner;

public class Division {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int divisible = Integer.parseInt(scanner.nextLine());
        int[] divisors = new int[]{2, 3, 6, 7, 10};
        int greatestDivisor = 0;

        for (int divisor : divisors) {
            if (divisible % divisor == 0) {
                greatestDivisor = divisor;
            }
        }

        if (greatestDivisor != 0) {
            System.out.printf("The number is divisible by %d", greatestDivisor);
        } else {
            System.out.println("Not divisible");
        }
    }
}