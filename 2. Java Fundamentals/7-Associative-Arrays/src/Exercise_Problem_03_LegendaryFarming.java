import java.util.*;

public class Exercise_Problem_03_LegendaryFarming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> keyMaterials = new LinkedHashMap<>();
        Map<String, Integer> junkMaterials = new LinkedHashMap<>();
        Map<String, String> legendaryItems = new HashMap<>();
        legendaryItems.put("shards", "Shadowmourne");
        legendaryItems.put("fragments", "Valanyr");
        legendaryItems.put("motes", "Dragonwrath");
        keyMaterials.put("shards", 0);
        keyMaterials.put("fragments", 0);
        keyMaterials.put("motes", 0);
        boolean isObtained = false;

        while (!isObtained) {
            String[] tokens = scanner.nextLine().split("\\s+");
            for (int i = 0; i < tokens.length; i += 2) {
                int materialQuantity = Integer.parseInt(tokens[i]);
                String item = tokens[i + 1].toLowerCase(Locale.ROOT);
                if (item.equals("shards") || item.equals("fragments") || item.equals("motes")) {
                    int currentQuantity = keyMaterials.get(item);
                    if (currentQuantity + materialQuantity >= 250) {
                        keyMaterials.put(item, currentQuantity + materialQuantity - 250);
                        System.out.printf("%s obtained!%n", legendaryItems.get(item));
                        isObtained = true;
                        break;
                    } else {
                        keyMaterials.put(item, currentQuantity + materialQuantity);
                    }
                } else {
                    junkMaterials.putIfAbsent(item, 0);
                    junkMaterials.put(item, junkMaterials.get(item) + materialQuantity);
                }
            }
        }

        keyMaterials.entrySet().stream()
                .forEach(i -> System.out.println(i.getKey() + ": " + i.getValue()));

        for (Map.Entry<String, Integer> entry : junkMaterials.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
