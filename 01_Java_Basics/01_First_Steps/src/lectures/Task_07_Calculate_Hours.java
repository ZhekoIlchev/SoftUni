package lectures;

import utils.InputUtils;

public class Task_07_Calculate_Hours {
    private static final int HOURS_PER_PROJECT = 3;

    public static void main(String[] args) {
        String name = InputUtils.readInput();
        int totalProjects = InputUtils.readInteger();
        int totalHours = totalProjects * HOURS_PER_PROJECT;

        String message = String.format("The architect %s will need %d hours to complete %d project/s.",
                name, totalHours, totalProjects);

        System.out.println(message);
    }
}