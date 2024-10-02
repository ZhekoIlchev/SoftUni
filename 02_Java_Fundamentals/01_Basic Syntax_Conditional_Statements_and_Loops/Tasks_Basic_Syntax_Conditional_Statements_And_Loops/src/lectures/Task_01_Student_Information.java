package lectures;

import java.util.Scanner;

public class Task_01_Student_Information {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        int age = Integer.parseInt(scanner.nextLine());
        double averageGrade = Double.parseDouble(scanner.nextLine());

        String message = String.format("Name: %s, Age: %d, Grade: %.2f", name, age, averageGrade);
        System.out.println(message);
    }
}