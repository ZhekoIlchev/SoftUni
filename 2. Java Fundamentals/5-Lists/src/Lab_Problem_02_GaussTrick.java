import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab_Problem_02_GaussTrick {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> numbers = new ArrayList<>();

        if (list.size() % 2 == 0) {

            for (int i = 0; i < list.size() / 2; i++) {
                numbers.add(i, list.get(i) + list.get(list.size() - 1 - i));
            }
        } else {

            for (int i = 0; i < list.size() / 2; i++) {
                numbers.add(i, list.get(i) + list.get(list.size() - 1 - i));
            }

            numbers.add(list.get(list.size() / 2));
        }

        for (Integer element : numbers) {
            System.out.print(element + " ");
        }
    }
}
