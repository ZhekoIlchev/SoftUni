package lectures;

import utils.InputUtil;

public class Task_04_Numbers_In_Range {
    public static void main(String[] args) {
        int number = InputUtil.readInteger();

        if (number < 100) {
            System.out.println("Less than 100");
        } else if (number >= 100 && number <= 200) {
            System.out.println("Betweem 100 and 200");
        } else {
            System.out.println("Greater than 200");
        }
    }
}