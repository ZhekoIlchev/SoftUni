import java.util.Scanner;

public class Problem_05_GreetingByName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read name from the console
        String name = scanner.nextLine();

        // Print
        System.out.printf("Hello, %s!", name);
    }
}
