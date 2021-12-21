import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab_Problem_01_SumAdjacentEqualNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Double> list = new ArrayList<>();
        String[] line = scanner.nextLine().split("\\s+");

        for (int i = 0; i < line.length; i++) {
            double number = Double.parseDouble(line[i]);
            list.add(number);
        }

        for (int i = 0; i < list.size() - 1; i++) {
            double currentNumber = list.get(i);
            double nextNumber = list.get(i + 1);

            if (currentNumber == nextNumber) {
                list.remove(i);
                list.set(i, currentNumber + nextNumber);
                i = -1;
            }
        }
        System.out.println(joinElementsByDelimiter(list));

    }

    public static String joinElementsByDelimiter(List<Double> list) {

        StringBuilder sb = new StringBuilder();
        DecimalFormat f = new DecimalFormat("0.#");

        for (Double element : list) {
            sb.append(f.format(element)).append(" ");
        }

        return sb.toString().trim();
    }
}
