package lectures;

import utils.InputUtils;

public class Task_05_Greeting {
    public static void main(String[] args) {
        String name = InputUtils.readInput();

        System.out.printf("Hello, %s!", name);
    }
}