import java.util.Scanner;

public class Problem_07_ProjectsCreation {
    private static final int HOURS_NEEDED_FOR_ONE_PROJECT = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String architectName = scanner.nextLine();
        int numberOfProjects = Integer.parseInt(scanner.nextLine());
        int totalHoursNeeded = numberOfProjects * HOURS_NEEDED_FOR_ONE_PROJECT;

        System.out.printf("The architect %s will need %d hours to complete %d project/s.", architectName, totalHoursNeeded, numberOfProjects);
    }
}
