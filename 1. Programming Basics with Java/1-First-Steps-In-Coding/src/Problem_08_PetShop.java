import java.util.Scanner;

public class Problem_08_PetShop {
    private static final double FOOD_PRICE_DOGS = 2.50;
    private static final double FOOD_PRICE_OTHER_ANIMALS = 4.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double numberOfDogs = Double.parseDouble(scanner.nextLine());
        double numberOfOtherAnimals = Double.parseDouble(scanner.nextLine());
        double totalSumNeeded = numberOfDogs * FOOD_PRICE_DOGS + numberOfOtherAnimals * FOOD_PRICE_OTHER_ANIMALS;

        System.out.printf("%.1f lv.", totalSumNeeded);
    }
}
