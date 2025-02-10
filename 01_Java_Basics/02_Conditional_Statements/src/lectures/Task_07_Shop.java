package lectures;

import utils.InputUtil;

public class Task_07_Shop {
    public static final double PUZZLE_PRICE = 2.60;
    public static final double DOLL_PRICE = 3.00;
    public static final double TEDDY_BEAR_PRICE = 4.10;
    public static final double MINION_PRICE = 8.20;
    public static final double TRUCK_PRICE = 2.00;
    public static final double DISCOUNT = 0.25;
    public static final double RENT = 0.1;

    public static void main(String[] args) {
        double tripPrice = InputUtil.readDouble();
        double puzzlesCount = InputUtil.readDouble();
        double dollsCount = InputUtil.readDouble();
        double teddyBearsCount = InputUtil.readDouble();
        double minionsCount = InputUtil.readDouble();
        double trucksCount = InputUtil.readDouble();

        double income = puzzlesCount * PUZZLE_PRICE + dollsCount * DOLL_PRICE + teddyBearsCount * TEDDY_BEAR_PRICE + minionsCount * MINION_PRICE + trucksCount * TRUCK_PRICE;
        double allToys = puzzlesCount + dollsCount + teddyBearsCount + minionsCount + trucksCount;

        if (allToys > 50) {
            income = income * (1 - DISCOUNT);
        }

        income = income * (1 - RENT);

        if (income >= tripPrice) {
            System.out.printf("Yes! %.2f lv left.", Math.abs(income - tripPrice));
        } else {
            System.out.printf("Not enough money! %.2f lv needed.", Math.abs(income - tripPrice));
        }
    }
}