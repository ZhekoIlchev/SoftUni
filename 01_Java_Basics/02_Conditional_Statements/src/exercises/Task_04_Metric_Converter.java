package exercises;

import utils.InputUtil;

public class Task_04_Metric_Converter {

    public static void main(String[] args) {
        int number = InputUtil.readInteger();
        String fromUnit = InputUtil.readInput();
        String toUnit = InputUtil.readInput();
        double convertedNumber = 0;

        if (!(fromUnit.equals("mm") || fromUnit.equals("cm") || fromUnit.equals("m"))) {
            System.out.println("Invalid input. It should be \"m\" or \"mm\".");
            return;
        }

        if (!(toUnit.equals("mm") || toUnit.equals("cm") || toUnit.equals("m"))) {
            System.out.println("Invalid input. It should be \"mm\", \"cm\" or \"m\".");
            return;
        }

        if (fromUnit.equals("mm")) {
            if (toUnit.equals("cm")) {
                convertedNumber = number / 10d;
            } else if (toUnit.equals("m")) {
                convertedNumber = number / 1000d;
            }

        } else if (fromUnit.equals("cm")) {
            if (toUnit.equals("mm")) {
                convertedNumber = number * 10;
            } else if (toUnit.equals("m")) {
                convertedNumber = number / 100d;
            }

        } else {
            if (toUnit.equals("mm")) {
                convertedNumber = number * 1000;
            } else if (toUnit.equals("cm")) {
                convertedNumber = number * 100;
            }

        }

        System.out.printf("%.3f", convertedNumber);
    }
}