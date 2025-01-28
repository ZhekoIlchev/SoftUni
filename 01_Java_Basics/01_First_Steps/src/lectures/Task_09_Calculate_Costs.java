package lectures;

import utils.InputUtils;

public class Task_09_Calculate_Costs {
    private static final double PRICE_PER_SQUARE_METER = 7.61;
    private static final double DISCOUNT_PERCENTAGE = 18;

    public static void main(String[] args) {
        double meters = InputUtils.readDouble();

        double totalPrice = meters * PRICE_PER_SQUARE_METER;
        double discount = totalPrice * (DISCOUNT_PERCENTAGE / 100);
        double finalPrice = totalPrice - discount;

        System.out.printf("The final price is: %.2f lv.%n", finalPrice);
        System.out.printf("The discount is: %.2f lv.", discount);
    }
}