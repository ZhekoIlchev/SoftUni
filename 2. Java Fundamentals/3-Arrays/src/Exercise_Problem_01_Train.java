import java.util.Scanner;

public class Exercise_Problem_01_Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int wagonsCount = Integer.parseInt(scanner.nextLine());
        int[] train = new int[wagonsCount];
        int people = 0;

        for (int i = 0; i < train.length; i++) {
            train[i] = Integer.parseInt(scanner.nextLine());
            people += train[i];
        }

        for (int wagons : train) {
            System.out.print(wagons + " ");
        }

        System.out.println();
        System.out.println(people);
    }
}
