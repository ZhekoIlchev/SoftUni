import java.util.Scanner;

public class Problem_09Ex_FishTank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());
        int width = Integer.parseInt(scanner.nextLine());

        int tankVolume = length * height * width;
        double volumeInLiters = tankVolume / 1000.0;

        double usagePercentage = Double.parseDouble(scanner.nextLine());

        double litersNeeded = volumeInLiters * (1 - usagePercentage / 100);

        System.out.println(litersNeeded);
    }
}