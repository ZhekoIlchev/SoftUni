import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab_Problem_03_MergingLists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> firstList = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> secondList = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> mergedList = new ArrayList<>();

        int index = 0;

        while (index < firstList.size() || index < secondList.size()) {

            if (indexInBoundaries(firstList, index)) {
                mergedList.add(firstList.get(index));
            }

            if (indexInBoundaries(secondList, index)) {
                mergedList.add(secondList.get(index));
            }

            index++;
        }

        for (Integer element : mergedList) {
            System.out.print(element + " ");
        }
    }

    private static boolean indexInBoundaries(List<Integer> list, int index) {

        return 0 <= index && index < list.size();
    }
}
