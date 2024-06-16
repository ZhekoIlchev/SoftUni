package exercise;

import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        String type = scanner.nextLine();
        String day = scanner.nextLine();
        double totalPrice = 0;

        switch (day) {
            case "Friday":
                totalPrice = calculatePriceForFriday(numberOfPeople, type);
                break;
            case "Saturday":
                totalPrice = calculatePriceForSaturday(numberOfPeople, type);
                break;
            case "Sunday":
                totalPrice = calculatePriceForSunday(numberOfPeople, type);
                break;
            default:
                System.out.println("Error!");
                break;
        }

        if (totalPrice != 0) {
            System.out.printf("Total price: %.2f", totalPrice);
        }
    }

    private static double calculatePriceForFriday(int numberOfPeople, String type) {
        double priceForStudents = 8.45;
        double priceForBusiness = 10.90;
        double priceForRegular = 15.00;
        double totalPrice = 0;

        if (type.equals("Students")) {
            totalPrice = priceForStudents * numberOfPeople;

            if (numberOfPeople >= 30) {
                totalPrice = totalPrice * (1 - 0.15);
            }
        }

        if (type.equals("Business")) {
            totalPrice = priceForBusiness * numberOfPeople;

            if (numberOfPeople >= 100) {
                totalPrice = totalPrice - 10 * priceForBusiness;
            }
        }

        if (type.equals("Regular")) {
            totalPrice = priceForRegular * numberOfPeople;

            if (10 <= numberOfPeople && numberOfPeople <= 20) {
                totalPrice = totalPrice * (1 - 0.05);
            }
        }

        return totalPrice;
    }

    private static double calculatePriceForSaturday(int numberOfPeople, String type) {
        double priceForStudents = 9.80;
        double priceForBusiness = 15.60;
        double priceForRegular = 20.00;
        double totalPrice = 0;

        if (type.equals("Students")) {
            totalPrice = priceForStudents * numberOfPeople;

            if (numberOfPeople >= 30) {
                totalPrice = totalPrice * (1 - 0.15);
            }
        }

        if (type.equals("Business")) {
            totalPrice = priceForBusiness * numberOfPeople;

            if (numberOfPeople >= 100) {
                totalPrice = totalPrice - 10 * priceForBusiness;
            }
        }

        if (type.equals("Regular")) {
            totalPrice = priceForRegular * numberOfPeople;

            if (10 <= numberOfPeople && numberOfPeople <= 20) {
                totalPrice = totalPrice * (1 - 0.05);
            }
        }

        return totalPrice;
    }

    private static double calculatePriceForSunday(int numberOfPeople, String type) {
        double priceForStudents = 10.46;
        double priceForBusiness = 16.00;
        double priceForRegular = 22.50;
        double totalPrice = 0;

        if (type.equals("Students")) {
            totalPrice = priceForStudents * numberOfPeople;

            if (numberOfPeople >= 30) {
                totalPrice = totalPrice * (1 - 0.15);
            }
        }

        if (type.equals("Business")) {
            totalPrice = priceForBusiness * numberOfPeople;

            if (numberOfPeople >= 100) {
                totalPrice = totalPrice - 10 * priceForBusiness;
            }
        }

        if (type.equals("Regular")) {
            totalPrice = priceForRegular * numberOfPeople;

            if (10 <= numberOfPeople && numberOfPeople <= 20) {
                totalPrice = totalPrice * (1 - 0.05);
            }
        }

        return totalPrice;
    }
}