package lectures;

import utils.InputUtil;

public class Task_05_Guess_Password {
    private static final String PASSWORD = "s3cr3t!P@ssw0rd";

    public static void main(String[] args) {
        String input = InputUtil.readInput();

        if (input.equals(PASSWORD)) {
            System.out.println("Welcome");
        } else {
            System.out.println("Wrong password!");
        }
    }
}