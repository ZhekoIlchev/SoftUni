package exercises;

import utils.InputUtils;

public class Task_02_Radians_To_Degrees {
    public static void main(String[] args) {
        double radians = InputUtils.readDouble();
        double degrees = calculateDegrees(radians);

        System.out.printf("%.0f", degrees);
    }

    private static double calculateDegrees(double radians) {
        return radians * 180 / Math.PI;
    }
}