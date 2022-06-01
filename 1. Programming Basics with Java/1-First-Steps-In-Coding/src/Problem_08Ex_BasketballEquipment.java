import java.util.Scanner;

public class Problem_08Ex_BasketballEquipment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int pricePerYear = Integer.parseInt(scanner.nextLine());
        double sneakers = pricePerYear - pricePerYear * 0.4;
        double basketballUniform = sneakers - sneakers * 0.20;
        double ball = basketballUniform * 1 / 4;
        double accessories = ball * 1 / 5;

        double totalCosts = pricePerYear + sneakers + basketballUniform + ball + accessories;
        System.out.println(totalCosts);
    }
}