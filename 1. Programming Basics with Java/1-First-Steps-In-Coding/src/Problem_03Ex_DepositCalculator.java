import java.util.Scanner;

public class Problem_03Ex_DepositCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double depositSum = Double.parseDouble(scanner.nextLine());
        int depositTime = Integer.parseInt(scanner.nextLine());
        double interestPerYear = Double.parseDouble(scanner.nextLine());

        double totalSum = depositSum + depositTime * ((depositSum * interestPerYear / 100) / 12);
        System.out.printf("%.2f", totalSum);
    }
}
