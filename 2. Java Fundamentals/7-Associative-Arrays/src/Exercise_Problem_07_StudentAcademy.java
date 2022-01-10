import java.util.*;

public class Exercise_Problem_07_StudentAcademy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Double>> grades = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String studentName = scanner.nextLine();
            double grade = Double.parseDouble(scanner.nextLine());

            grades.putIfAbsent(studentName, new ArrayList<>());
            grades.get(studentName).add(grade);
        }

        grades.entrySet()
                .stream()
                .filter(g -> g.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble() >= 4.50)
                .sorted((f, s) -> {
                    double firstAverage = f.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
                    double secondAverage = s.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
                    return Double.compare(secondAverage, firstAverage);
                })
                .forEach(s -> {
                    double averageGrade = s.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
                    System.out.printf("%s -> %.2f%n", s.getKey(), averageGrade);
                });

    }
}
