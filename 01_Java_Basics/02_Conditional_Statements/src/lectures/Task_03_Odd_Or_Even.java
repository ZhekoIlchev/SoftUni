package lectures;

import utils.InputUtil;

public class Task_03_Odd_Or_Even {
    public static void main(String[] args) {
        int number = InputUtil.readInteger();

        if (number % 2 == 0) {
            System.out.println("even");
        } else {
            System.out.println("odd");
        }
    }
}