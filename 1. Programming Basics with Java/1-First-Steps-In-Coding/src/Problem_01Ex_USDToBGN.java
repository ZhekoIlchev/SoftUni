import java.util.Scanner;

public class Problem_01Ex_USDToBGN {
    // USD -> BGN
    private static final double EXCHANGE_RATE = 1.79549;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double usd = Double.parseDouble(scanner.nextLine());
        double bgn = usd * EXCHANGE_RATE;
        System.out.println(bgn);
    }
}
