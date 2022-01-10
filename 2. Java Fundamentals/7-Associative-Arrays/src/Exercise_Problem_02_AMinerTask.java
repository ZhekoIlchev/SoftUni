import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercise_Problem_02_AMinerTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> resources = new LinkedHashMap<>();

        while (true) {
            String resource = scanner.nextLine();
            if (!resource.equals("stop")) {
                resources.putIfAbsent(resource, 0);
                int quantity = Integer.parseInt(scanner.nextLine());
                int currentQuantity = resources.get(resource);
                resources.put(resource, currentQuantity + quantity);
            } else {
                break;
            }
        }
        resources.entrySet().stream()
                .forEach(r -> System.out.println(r.getKey() + " -> " + r.getValue()));
    }
}
