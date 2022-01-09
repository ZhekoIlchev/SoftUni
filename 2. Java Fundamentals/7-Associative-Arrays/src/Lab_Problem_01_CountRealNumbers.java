import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Lab_Problem_01_CountRealNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Double, Integer> numbers = new TreeMap<>();
        double[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();

        for (int i = 0; i < array.length; i++) {
            double currentNumber = array[i];
            Integer isPresent = numbers.get(currentNumber);
            if (isPresent == null) {
                numbers.put(currentNumber, 1);
            } else {
                numbers.put(currentNumber, isPresent + 1);
            }
        }

        for (Map.Entry<Double, Integer> entry : numbers.entrySet()) {
            DecimalFormat df = new DecimalFormat("#.#######");
            System.out.printf("%s -> %d%n", df.format(entry.getKey()), entry.getValue());
        }
    }
}
