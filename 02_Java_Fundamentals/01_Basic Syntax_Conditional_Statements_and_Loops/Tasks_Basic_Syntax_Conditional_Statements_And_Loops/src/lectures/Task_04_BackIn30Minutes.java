package lectures;

import java.util.Scanner;

public class Task_04_BackIn30Minutes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hour = Integer.parseInt(scanner.nextLine());
        int minutes = Integer.parseInt(scanner.nextLine());
        minutes += 30;

        if (minutes > 60) {
            hour++;

            if (hour > 23) {
                hour = 0;
            }

            minutes -= 60;
        }

        System.out.printf("%d:%02d", hour, minutes);
    }
}