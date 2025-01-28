package exercises;

import utils.InputUtils;

public class Task_01_USD_To_BGN {
    private static final double USD_TO_BGN_EXCHANGE_RATE = 1.79549;

    public static void main(String[] args) {
        double usd = InputUtils.readDouble();
        double bgn = usd * USD_TO_BGN_EXCHANGE_RATE;

        System.out.println(bgn);
    }
}