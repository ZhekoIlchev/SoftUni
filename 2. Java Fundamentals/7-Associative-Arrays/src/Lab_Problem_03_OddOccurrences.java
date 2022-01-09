import java.util.*;

public class Lab_Problem_03_OddOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        Map<String, Integer> occurrences = new LinkedHashMap<>();

        for (int i = 0; i < input.length; i++) {
            String currentString = input[i].toLowerCase(Locale.ROOT);
            occurrences.putIfAbsent(currentString, 0);
            occurrences.put(currentString, occurrences.get(currentString) + 1);
        }

        List<String> list = new ArrayList<>();
        occurrences.entrySet().stream()
                .filter(o -> o.getValue() % 2 != 0)
                .forEach(o -> list.add(o.getKey()));

        System.out.println(String.join(", ", list));
    }
}
