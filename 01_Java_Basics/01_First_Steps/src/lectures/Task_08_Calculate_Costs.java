package lectures;

import utils.InputUtils;

public class Task_08_Calculate_Costs {
    private static final double DOG_FOOD_PRICE = 2.50;
    private static final double OTHER_ANIMAL_FOOD_PRICE = 4.00;

    public static void main(String[] args) {
        int dogs = InputUtils.readInteger();
        int otherAnimals = InputUtils.readInteger();
        double totalSum = dogs * DOG_FOOD_PRICE + otherAnimals * OTHER_ANIMAL_FOOD_PRICE;

        System.out.printf("%.2f lv.", totalSum);
    }
}
