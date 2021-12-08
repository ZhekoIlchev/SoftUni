import java.util.Scanner;

public class Problem_04_Inches_To_Centimeters {
    // One inch is 2.54 centimeters
    public static double ONE_INCH = 2.54;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert number:");
        double number = Double.parseDouble(scanner.nextLine());

        // Calculate
        double centimeters = number * ONE_INCH;
        System.out.printf("%.2f inches are %.1f centimeters.", number, centimeters);
    }
}
