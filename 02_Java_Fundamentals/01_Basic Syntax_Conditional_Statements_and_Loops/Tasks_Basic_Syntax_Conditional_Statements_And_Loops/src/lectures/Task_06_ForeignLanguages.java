package lectures;

import java.util.Scanner;

public class Task_06_ForeignLanguages {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();

        switch (country) {
            case "England", "USA" -> System.out.println("English");
            case "Spain", "Argentina", "Mexico" -> System.out.println("Spanish");
            default -> System.out.println("unknown");
        }
    }
}