package lectures;

import utils.InputUtil;

public class Task_02_Greater_Number {
    public static void main(String[] args) {
        int num1 = InputUtil.readInteger();
        int num2 = InputUtil.readInteger();

        int greaterNumber = num1 >= num2 ? num1 : num2;
        System.out.println(greaterNumber);
    }
}