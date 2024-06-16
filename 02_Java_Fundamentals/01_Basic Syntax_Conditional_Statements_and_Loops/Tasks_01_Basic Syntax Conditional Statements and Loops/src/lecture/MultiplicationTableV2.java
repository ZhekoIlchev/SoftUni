package lecture;

import java.util.Scanner;

public class MultiplicationTableV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        int multiplier = Integer.parseInt(scanner.nextLine());
        int product;

        if (multiplier > 10) {
            product = number * multiplier;
            System.out.println(number + " X " + multiplier + " = " + product);
        } else {
            for (int i = multiplier; i <= 10; i++) {
                product = number * i;
                System.out.println(number + " X " + i + " = " + product);
            }
        }
    }
}