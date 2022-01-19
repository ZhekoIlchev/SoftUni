import java.util.Scanner;

public class Problem_04Ex_VacationBooksList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfPages = Integer.parseInt(scanner.nextLine());
        int pagesPerHour = Integer.parseInt(scanner.nextLine());
        int days = Integer.parseInt(scanner.nextLine());

        int totalHoursNeeded = numberOfPages / pagesPerHour;
        int readingHoursNeededPerDay = totalHoursNeeded / days;

        System.out.println(readingHoursNeededPerDay);
    }
}
