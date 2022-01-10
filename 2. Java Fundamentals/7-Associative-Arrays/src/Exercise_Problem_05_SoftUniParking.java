import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercise_Problem_05_SoftUniParking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, String> parkingValidation = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split("\\s+");
            String type = command[0];
            String userName = command[1];

            switch (type) {
                case "register":
                    String licensePlateNumber = command[2];
                    if (!parkingValidation.containsKey(userName)) {
                        parkingValidation.put(userName, licensePlateNumber);
                        System.out.printf("%s registered %s successfully%n", userName, licensePlateNumber);
                    } else {
                        System.out.printf("ERROR: already registered with plate number %s%n", licensePlateNumber);
                    }
                    break;
                case "unregister":
                    if (parkingValidation.containsKey(userName)) {
                        parkingValidation.remove(userName);
                        System.out.printf("%s unregistered successfully%n", userName);
                    } else {
                        System.out.printf("ERROR: user %s not found%n", userName);
                    }
                    break;
            }
        }

        for (Map.Entry<String, String> entry : parkingValidation.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}
