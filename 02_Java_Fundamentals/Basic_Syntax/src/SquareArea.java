import java.util.Scanner;

public class SquareArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double side = Double.parseDouble(scanner.nextLine());
        double area = side * side;
        System.out.println(String.format("The area of the square is: %.2f", area));
    }
}