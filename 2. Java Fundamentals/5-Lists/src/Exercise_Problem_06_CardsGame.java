import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Exercise_Problem_06_CardsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> firstPlayer = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> secondPlayer = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        while (!(firstPlayer.isEmpty() || secondPlayer.isEmpty())) {
            int cardFirstPlayer = firstPlayer.get(0);
            int cardSecondPlayer = secondPlayer.get(0);
            firstPlayer.remove(0);
            secondPlayer.remove(0);

            if (cardFirstPlayer > cardSecondPlayer) {
                firstPlayer.add(cardFirstPlayer);
                firstPlayer.add(cardSecondPlayer);
            } else if (cardSecondPlayer > cardFirstPlayer) {
                secondPlayer.add(cardSecondPlayer);
                secondPlayer.add(cardFirstPlayer);
            }
        }

        int sum = 0;
        if (!firstPlayer.isEmpty()) {
            for (Integer card : firstPlayer) {
                sum += card;
            }
            System.out.printf("First player wins! Sum: %d", sum);
        } else {
            for (Integer card : secondPlayer) {
                sum += card;
            }
            System.out.printf("Second player wins! Sum: %d", sum);
        }
    }
}
