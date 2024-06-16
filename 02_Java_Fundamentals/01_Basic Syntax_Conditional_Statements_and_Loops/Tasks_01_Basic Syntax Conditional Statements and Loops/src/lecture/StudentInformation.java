package lecture;

import java.util.Scanner;

public class StudentInformation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String studentName = scanner.nextLine();
        int studentAge = Integer.parseInt(scanner.nextLine());
        double studentGrade = Double.parseDouble(scanner.nextLine());
        printStudentInformation(studentName, studentAge, studentGrade);
    }

    private static void printStudentInformation(String name, int age, double grade) {
        System.out.printf("Name: %s, Age: %d, Grade: %.2f", name, age, grade);
    }
}