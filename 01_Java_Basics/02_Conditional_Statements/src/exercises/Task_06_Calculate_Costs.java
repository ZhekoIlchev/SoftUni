package exercises;

import utils.InputUtil;

public class Task_06_Calculate_Costs {
    public static void main(String[] args) {
        double budget = InputUtil.readDouble();
        int people = InputUtil.readInteger();
        double cloths = InputUtil.readDouble();

        double decoration = budget * 10 / 100;
        double clothingExpenses = people * cloths;

        if (people > 150) {
            clothingExpenses = clothingExpenses * (1 - 10 / 100d);
        }

        double totalCosts = decoration + clothingExpenses;

        if (totalCosts <= budget) {
            System.out.println("Action!");
            System.out.printf("Wingard starts filming with %.2f leva left.", budget - totalCosts);
        } else {
            System.out.println("Not enough money!");
            System.out.printf("Wingard needs %.2f leva more.", Math.abs(budget - totalCosts));
        }
    }
}