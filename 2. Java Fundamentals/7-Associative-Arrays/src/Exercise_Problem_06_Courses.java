import java.util.*;

public class Exercise_Problem_06_Courses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> courses = new LinkedHashMap<>();
        String command;

        while (!"end".equals(command = scanner.nextLine())) {
            String[] courseInfo = command.split(" : ");
            String courseName = courseInfo[0];
            String studentName = courseInfo[1];
            if (!courses.containsKey(courseName)) {
                courses.put(courseName, new ArrayList<>());
            }
            courses.get(courseName).add(studentName);
        }

        courses.entrySet()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getValue().size(), a.getValue().size()))
                .forEach(c -> {
                    System.out.println(c.getKey() + ": " + c.getValue().size());
                    c.getValue()
                            .stream()
                            .sorted((f, s) -> f.compareTo(s))
                            .forEach(s -> System.out.println("-- " + s));
                });
    }
}
