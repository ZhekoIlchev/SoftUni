import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Lab_Problem_06_ListOfProducts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        List<String> products = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            String product = scanner.nextLine();
            products.add(product);
        }

        AtomicInteger order = new AtomicInteger(1);
        products.stream().sorted((firstProduct, secondProduct) -> firstProduct.compareTo(secondProduct))
                .forEach(p -> System.out.printf("%d.%s%n", order.getAndIncrement(), p));
    }
}
