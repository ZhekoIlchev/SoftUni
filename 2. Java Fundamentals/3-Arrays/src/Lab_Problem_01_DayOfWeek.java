import java.util.Scanner;

public class Lab_Problem_01_DayOfWeek {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] dayNames = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday"};

        int input = Integer.parseInt(scanner.nextLine());

        if (1 <= input && input <= 7) {
            System.out.println(dayNames[input - 1]);
        } else {
            System.out.println("Invalid day!");
        }
    }
}
