package lectures;

import java.util.Locale;
import java.util.Scanner;

public class Task_07_TheatrePromotions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String day = scanner.nextLine().toLowerCase(Locale.ROOT);
        int age = Integer.parseInt(scanner.nextLine());
        int price = 0;

        if (age < 0) {
            System.out.println("Error!");
            return;
        }

        if (age <= 18) {
            if (day.equals("weekday")) {
                price = 12;
            } else if (day.equals("weekend")) {
                price = 15;
            } else if (day.equals("holiday")) {
                price = 5;
            }
        } else if (age <= 64) {
            if (day.equals("weekday")) {
                price = 18;
            } else if (day.equals("weekend")) {
                price = 20;
            } else if (day.equals("holiday")) {
                price = 12;
            }
        } else if (age <= 122) {
            if (day.equals("weekday")) {
                price = 12;
            } else if (day.equals("weekend")) {
                price = 15;
            } else if (day.equals("holiday")) {
                price = 10;
            }
        }

        System.out.println(price + "$");
    }
}