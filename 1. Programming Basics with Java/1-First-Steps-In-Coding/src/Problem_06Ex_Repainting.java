import java.util.Scanner;

public class Problem_06Ex_Repainting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double protectiveNylonPricePerSquareMeter = 1.50;
        double paintPricePerLiter = 14.50;
        double paintThinnerPricePerLiter = 5.00;
        double bags = 0.40;

        int protectiveNylon = Integer.parseInt(scanner.nextLine());
        int paint = Integer.parseInt(scanner.nextLine());
        int paintThinner = Integer.parseInt(scanner.nextLine());

        double costsForNylon = (protectiveNylon + 2) * protectiveNylonPricePerSquareMeter;
        double costsForPaint = (paint + paint * 0.1) * paintPricePerLiter;
        double costsForPaintThinner = paintThinner * paintThinnerPricePerLiter;

        double totalCostsForMaterials = costsForNylon + costsForPaint + costsForPaintThinner + bags;

        int workHours = Integer.parseInt(scanner.nextLine());
        double totalCostsForWork = (totalCostsForMaterials * 0.3) * workHours;

        double totalCosts = totalCostsForMaterials + totalCostsForWork;
        System.out.println(totalCosts);
    }
}