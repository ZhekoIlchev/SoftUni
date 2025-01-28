package exercises;

import utils.InputUtils;

public class Task_08_Aquarium {
    public static void main(String[] args) {
        double length = InputUtils.readDouble();
        double width = InputUtils.readDouble();
        double height = InputUtils.readDouble();
        double occupiedCapacityPercentage = InputUtils.readDouble();

        double volume = length * width * height;
        double water = (volume * 0.001) * (1 - occupiedCapacityPercentage / 100);

        System.out.printf("%.2f", water);
    }
}