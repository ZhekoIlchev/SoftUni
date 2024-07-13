import java.util.Scanner;

public class CalculateSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());
        System.out.println(num1 - num2);
    }
}