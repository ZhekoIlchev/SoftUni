package exercises;

import utils.InputUtil;

public class Task_01_Calculate_Seconds {
    public static void main(String[] args) {
        int timeFirst = InputUtil.readInteger();
        int timeSecond = InputUtil.readInteger();
        int timeThird = InputUtil.readInteger();

        int totalTime = timeFirst + timeSecond + timeThird;
        int minutes = totalTime / 60;
        int seconds = totalTime % 60;

        System.out.printf("%d:%02d", minutes, seconds);
    }
}