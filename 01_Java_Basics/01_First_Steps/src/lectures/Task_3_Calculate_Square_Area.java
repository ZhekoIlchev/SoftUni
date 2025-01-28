package lectures;

import java.util.Scanner;

public class Task_3_Calculate_Square_Area {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int side = Integer.parseInt(scanner.nextLine());

        int area = side * side;
        System.out.println(area);
    }
}