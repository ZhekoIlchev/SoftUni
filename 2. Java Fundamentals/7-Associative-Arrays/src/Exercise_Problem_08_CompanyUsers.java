import java.util.*;

public class Exercise_Problem_08_CompanyUsers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> information = new LinkedHashMap<>();
        String command;

        while (!"End".equals(command = scanner.nextLine())) {
            String[] tokens = command.split(" -> ");
            String companyName = tokens[0];
            String employeeName = tokens[1];

            information.putIfAbsent(companyName, new ArrayList<>());
            if (!information.get(companyName).contains(employeeName)) {
                information.get(companyName).add(employeeName);
            }
        }

        information.entrySet()
                .stream()
                .forEach(c -> {
                    System.out.println(c.getKey());
                    for (String employee : c.getValue()) {
                        System.out.println("-- " + employee);
                    }
                });
    }
}
