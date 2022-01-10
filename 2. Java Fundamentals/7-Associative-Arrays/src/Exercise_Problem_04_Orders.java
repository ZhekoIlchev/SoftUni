import java.util.*;

public class Exercise_Problem_04_Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<Double>> products = new LinkedHashMap<>();
        String command;

        while (!"buy".equals(command = scanner.nextLine())) {
            String[] tokens = command.split("\\s+");
            String product = tokens[0];
            double price = Double.parseDouble(tokens[1]);
            double quantity = Double.parseDouble(tokens[2]);
            if (!products.containsKey(product)) {
                List<Double> productInfo = new ArrayList<>();
                productInfo.add(price);
                productInfo.add(quantity);
                products.put(product, productInfo);
            } else {
                double currentPrice = products.get(product).get(0);
                double currentQuantity = products.get(product).get(1);
                if (currentPrice != price) {
                    products.get(product).set(0, price);
                }
                products.get(product).set(1, currentQuantity + quantity);
            }
        }

        for (Map.Entry<String, List<Double>> entry : products.entrySet()) {
            double totalPrice = entry.getValue().get(0) * entry.getValue().get(1);
            System.out.printf("%s -> %.2f%n", entry.getKey(), totalPrice);
        }
    }
}
