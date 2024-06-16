package exercise;

import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    private static final List<Double> ACCEPTABLE_COINS = List.of(0.1, 0.2, 0.5, 1.0, 2.0);
    private static final double NUTS_PRICE = 2.0;
    private static final double WATER_PRICE = 0.7;
    private static final double CRISPS_PRICE = 1.5;
    private static final double SODA_PRICE = 0.8;
    private static final double COKE_PRICE = 1.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        double totalSum = 0.0;


        while (!"Start".equals(input = scanner.nextLine())) {
            double currentCoin = 0.0;

            if (input.length() == 3) {
                currentCoin = Double.parseDouble(input);
            } else if (input.length() == 1) {
                currentCoin = Integer.parseInt(input) * 1.0;
            }

            if (ACCEPTABLE_COINS.contains(currentCoin)) {
                totalSum += currentCoin;
            } else {
                System.out.printf("Cannot accept %.2f%n", currentCoin);
            }
        }

        while (!"End".equals(input = scanner.nextLine())) {
            switch (input) {
                case "Nuts":
                    if (isTotalSumEnough(totalSum, NUTS_PRICE)) {
                        totalSum -= NUTS_PRICE;
                        System.out.println("Purchased Nuts");
                    } else {
                        System.out.println("Sorry, not enough money");
                    }
                    break;
                case "Water":
                    if (isTotalSumEnough(totalSum, WATER_PRICE)) {
                        totalSum -= WATER_PRICE;
                        System.out.println("Purchased Water");
                    } else {
                        System.out.println("Sorry, not enough money");
                    }
                    break;
                case "Crisps":
                    if (isTotalSumEnough(totalSum, CRISPS_PRICE)) {
                        totalSum -= CRISPS_PRICE;
                        System.out.println("Purchased Crisps");
                    } else {
                        System.out.println("Sorry, not enough money");
                    }
                    break;
                case "Soda":
                    if (isTotalSumEnough(totalSum, SODA_PRICE)) {
                        totalSum -= SODA_PRICE;
                        System.out.println("Purchased Soda");
                    } else {
                        System.out.println("Sorry, not enough money");
                    }
                    break;
                case "Coke":
                    if (isTotalSumEnough(totalSum, COKE_PRICE)) {
                        totalSum -= COKE_PRICE;
                        System.out.println("Purchased Coke");
                    } else {
                        System.out.println("Sorry, not enough money");
                    }
                    break;
                default:
                    System.out.println("Invalid product");
                    break;
            }
        }

        System.out.printf("Change: %.2f", totalSum);
    }

    private static boolean isTotalSumEnough(double totalSum, double productPrice) {
        return totalSum >= productPrice;
    }
}