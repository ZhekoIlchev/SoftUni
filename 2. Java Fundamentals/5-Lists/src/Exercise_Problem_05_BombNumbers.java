import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Exercise_Problem_05_BombNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int[] bombInfo = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int bomb = bombInfo[0];
        int power = bombInfo[1];

        while (list.contains(bomb)) {

            int indexOfBomb = list.indexOf(bomb);
            int leftBound = Math.max(0, indexOfBomb - power);
            int rightBound = Math.min(indexOfBomb + power, list.size() - 1);

            for (int i = rightBound; i >= leftBound; i--) {
                list.remove(i);
            }
        }

        int sum = 0;
        for (Integer number : list) {
            sum += number;
        }

        System.out.println(sum);
    }
}
