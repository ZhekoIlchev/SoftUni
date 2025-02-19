package exercises;

import utils.InputUtil;

public class Task_07_World_Record {
    public static void main(String[] args) {
        double worldRecord = InputUtil.readDouble();
        double distance = InputUtil.readDouble();
        double secondsPerOneMeter = InputUtil.readDouble();

        double timeNeeded = distance * secondsPerOneMeter;
        double cumulativeDelay = Math.floor(distance / 15) * 12.5;

        double totalTime = timeNeeded + cumulativeDelay;

        if (totalTime < worldRecord) {
            System.out.printf("Yes, he succeeded! The new world record is %.2f seconds.", totalTime);
        } else {
            System.out.printf("No, he failed! He was %.2f seconds slower.", Math.abs(totalTime - worldRecord));
        }
    }
}