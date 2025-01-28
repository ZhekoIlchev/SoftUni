package lectures;

import java.util.Scanner;

public class Task_04_Inches_To_Centimeters {
    private static final double INCH_TO_CM_CONVERSION_FACTOR = 2.54;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double inch = Double.parseDouble(scanner.nextLine());

        double centimeters = inch * INCH_TO_CM_CONVERSION_FACTOR;
        System.out.println(centimeters);
    }
}