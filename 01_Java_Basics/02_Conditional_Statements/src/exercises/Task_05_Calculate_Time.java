package exercises;

import utils.InputUtil;

public class Task_05_Calculate_Time {
    public static void main(String[] args) {
        int hour = InputUtil.readInteger();
        int minutes = InputUtil.readInteger();

        /**
         * Calculate minutes, after 15 min.
         */

        minutes += 15;

        if (minutes >= 60) {
            hour++;
            if (hour >= 24) {
                hour -= 24;
            }
            minutes -= 60;
        }

        System.out.printf("%d:%02d", hour, minutes);
    }
}