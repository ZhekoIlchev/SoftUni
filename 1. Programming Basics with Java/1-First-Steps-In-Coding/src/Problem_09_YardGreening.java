import java.util.Scanner;

public class Problem_09_YardGreening {
    private static final double PERCENTAGE_DISCOUNT = 0.18;
    private static final double PRICE_FOR_ONE_SQMeter = 7.61;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double totalMeters = Double.parseDouble(scanner.nextLine());
        double totalSumNeededWithoutDiscount = totalMeters * PRICE_FOR_ONE_SQMeter;
        double discount = totalSumNeededWithoutDiscount * PERCENTAGE_DISCOUNT;
        double finalSumNeeded = totalMeters * PRICE_FOR_ONE_SQMeter - discount;

        System.out.printf("The final price is: %.2f lv.%n", finalSumNeeded);
        System.out.printf("The discount is: %.2f lv.", discount);
    }
}
