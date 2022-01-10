import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercise_Problem_01_CountCharsInAString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<Character, Integer> chars = new LinkedHashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar != ' ') {
                chars.putIfAbsent(currentChar, 0);
                chars.put(currentChar, chars.get(currentChar) + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : chars.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
