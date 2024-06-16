package exercise;

import java.util.Scanner;

public class TriangleOfNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            printNTimes(i);
        }
    }

    private static void printNTimes(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}