import java.text.DecimalFormat;
import java.util.Scanner;

public class Problem_05Ex_SuppliesForSchool {
    private static final double PRICE_PER_PENCIL_PACK = 5.80;
    private static final double PRICE_PER_MARKER_PACK = 7.20;
    private static final double PRICE_PER_LITER_CLEANING_DETERGENT = 1.20;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfPencilPack = Integer.parseInt(scanner.nextLine());
        int numberOfMarkerPack = Integer.parseInt(scanner.nextLine());
        int litersOfDetergent = Integer.parseInt(scanner.nextLine());
        int discount = Integer.parseInt(scanner.nextLine());

        double totalSum = (numberOfPencilPack * PRICE_PER_PENCIL_PACK
                + numberOfMarkerPack * PRICE_PER_MARKER_PACK
                + litersOfDetergent * PRICE_PER_LITER_CLEANING_DETERGENT) * (1 - discount / 100.00);

        DecimalFormat df = new DecimalFormat("#.#######");
        System.out.printf("%s", df.format(totalSum));
    }
}
