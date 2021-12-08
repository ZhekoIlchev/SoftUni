import java.util.Scanner;

public class Problem_03_RectangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert width:");
        int width = Integer.parseInt(scanner.nextLine());

        System.out.println("Insert length:");
        int length = Integer.parseInt(scanner.nextLine());

        // Calculate square area
        int area = width * length;

        // Print square area
        System.out.printf("The area of rectangle with sides %d and %d is %d.", width, length, area);
    }
}
