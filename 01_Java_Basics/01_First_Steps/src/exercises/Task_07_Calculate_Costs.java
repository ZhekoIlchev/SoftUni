package exercises;

import utils.InputUtils;

public class Task_07_Calculate_Costs {
    public static void main(String[] args) {
        double strawberryPrice = InputUtils.readDouble();
        double raspberryPrice = strawberryPrice * (1 - 50 / 100d);
        double orangePrice = raspberryPrice * (1 - 40 / 100d);
        double bananaPrice = raspberryPrice * (1 - 80 / 100d);

        double bananasInKg = InputUtils.readDouble();
        double orangesInKg = InputUtils.readDouble();
        double raspberriesInKg = InputUtils.readDouble();
        double strawberriesInKg = InputUtils.readDouble();

        double totalSum = bananaPrice * bananasInKg + orangePrice * orangesInKg + raspberryPrice * raspberriesInKg + strawberryPrice * strawberriesInKg;
        System.out.printf("%.2f", totalSum);
    }
}