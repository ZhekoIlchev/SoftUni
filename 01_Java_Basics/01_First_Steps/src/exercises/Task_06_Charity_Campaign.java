package exercises;

import utils.InputUtils;

public class Task_06_Charity_Campaign {
    private static final double CAKE_PRICE = 45.00;
    private static final double WAFFLE_PRICE = 5.80;
    private static final double PANCAKE_PRICE = 3.20;

    public static void main(String[] args) {
        int days = InputUtils.readInteger();
        int pastryChefs = InputUtils.readInteger();
        int cakesCount = InputUtils.readInteger();
        int wafflesCount = InputUtils.readInteger();
        int pancakesCount = InputUtils.readInteger();

        double cakeRevenue = cakesCount * CAKE_PRICE;
        double waffleRevenue = wafflesCount * WAFFLE_PRICE;
        double pancakeRevenue = pancakesCount * PANCAKE_PRICE;

        double totalRevenuePerDay = cakeRevenue + waffleRevenue + pancakeRevenue;
        double raisedAmount = totalRevenuePerDay * pastryChefs * days;

        double costs = raisedAmount * 1 / 8d;
        System.out.println(raisedAmount - costs);
    }
}