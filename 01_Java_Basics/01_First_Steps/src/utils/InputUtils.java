package utils;

import java.util.Scanner;

public class InputUtils {
    private static final Scanner SCANNER = new Scanner(System.in);


    public static String readInput() {

        return SCANNER.nextLine();
    }

    public static int readInteger() {

        return Integer.parseInt(SCANNER.nextLine());
    }

    public static double readDouble() {

        return Double.parseDouble(SCANNER.nextLine());
    }
}