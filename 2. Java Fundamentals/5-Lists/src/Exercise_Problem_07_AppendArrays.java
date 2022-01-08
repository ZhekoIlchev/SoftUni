import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Exercise_Problem_07_AppendArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\|");
        List<List<String>> list = new ArrayList<>();

        for (String element : input) {
            String[] currentElement = element.split("\\s+");
            List<String> currentList = new ArrayList<>();
            for (int i = 0; i < currentElement.length; i++) {
                if (!currentElement[i].equals("")) {
                    currentList.add(currentElement[i].trim());
                }
            }

            list.add(currentList);
        }

        Collections.reverse(list);
        for (List<String> element : list) {
            if (!element.isEmpty()) {
                StringBuilder sb = new StringBuilder(String.join(" ", element));
                sb.append(" ");
                System.out.print(sb);
            }
        }
    }
}
