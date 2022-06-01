import java.util.Scanner;

public class Problem_07Ex_FoodDelivery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double chickenMealPrice = 10.35;
        double fishMealPrice = 12.40;
        double vegetarianMealPrice = 8.15;

        int countOfChickenMeal = Integer.parseInt(scanner.nextLine());
        int countOfFishMeal = Integer.parseInt(scanner.nextLine());
        int countOfVegetarianMeal = Integer.parseInt(scanner.nextLine());

        double totalPriceForChickenMeal = countOfChickenMeal * chickenMealPrice;
        double totalPriceForFishMeal = countOfFishMeal * fishMealPrice;
        double totalPriceForVegetarianMeal = countOfVegetarianMeal * vegetarianMealPrice;

        double totalPriceForMeals = totalPriceForChickenMeal + totalPriceForFishMeal + totalPriceForVegetarianMeal;
        double desert = totalPriceForMeals * 0.2;
        double delivery = 2.50;
        double totalCosts = totalPriceForMeals + desert + delivery;

        System.out.println(totalCosts);
    }
}