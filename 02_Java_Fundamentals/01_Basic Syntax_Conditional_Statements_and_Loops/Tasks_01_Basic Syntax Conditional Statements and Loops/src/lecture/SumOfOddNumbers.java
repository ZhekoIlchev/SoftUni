package lecture;

import java.util.Scanner;

public class SumOfOddNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            int oddNumber = i * 2 - 1;
            System.out.println(oddNumber);
            sum += oddNumber;
        }

        System.out.println("Sum: " + sum);
    }
}