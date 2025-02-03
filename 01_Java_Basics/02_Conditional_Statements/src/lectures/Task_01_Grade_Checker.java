package lectures;

import utils.InputUtil;

public class Task_01_Grade_Checker {
    public static void main(String[] args) {
        int grade = InputUtil.readInteger();

        if (grade >= 5) {
            System.out.println("Excellent!");
        }
    }
}