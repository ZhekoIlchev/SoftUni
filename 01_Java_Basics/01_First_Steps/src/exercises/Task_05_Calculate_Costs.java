package exercises;

import utils.InputUtils;

public class Task_05_Calculate_Costs {
    private static final double CAKE_COSTS_PERCENTAGE = 20 / 100d;
    private static final double BEVERAGES_COSTS_PERCENTAGE = 45 / 100d;
    private static final double ANIMATOR_COSTS_PERCENTAGE = 1 / 3d;

    public static void main(String[] args) {
        int rentHall = InputUtils.readInteger();
        double cakeCosts = rentHall * CAKE_COSTS_PERCENTAGE;
        double beverageCosts = cakeCosts * (1 - BEVERAGES_COSTS_PERCENTAGE);
        double animatorCosts = rentHall * ANIMATOR_COSTS_PERCENTAGE;

        double totalCosts = rentHall + cakeCosts + beverageCosts + animatorCosts;
        System.out.println(totalCosts);
    }
}