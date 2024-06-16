package exercise;

import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String username = scanner.nextLine();
        String password = reverseUsername(username);

        for (int i = 0; i <= 3; i++) {
            String givenPassword = scanner.nextLine();

            if (password.equals(givenPassword)) {
                System.out.printf("User %s logged in.", username);
                return;
            } else {
                if (i == 3) {
                    System.out.printf("User %s blocked!", username);
                } else {
                    System.out.println("Incorrect password. Try again.");
                }
            }
        }
    }

    public static String reverseUsername(String username) {
        String reversedUsername = "";

        for (int i = username.length() - 1; i >= 0; i--) {
            reversedUsername += username.charAt(i);
        }

        return reversedUsername;
    }
}