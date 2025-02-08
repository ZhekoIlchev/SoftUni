package lectures;

import utils.InputUtil;

public class Task_06_Calculate_Area {
    public static void main(String[] args) {
        String figure = InputUtil.readInput();
        double area = 0;

        if (figure.equals("square")) {
            double side = InputUtil.readDouble();
            area = side * side;

        } else if (figure.equals("rectangle")) {
            double height = InputUtil.readDouble();
            double width = InputUtil.readDouble();
            area = height * width;

        } else if (figure.equals("circle")) {
            double radius = InputUtil.readDouble();
            area = Math.PI * Math.pow(radius, 2);

        } else if (figure.equals("triangle")) {
            double side = InputUtil.readDouble();
            double height = InputUtil.readDouble();
            area = (side * height) / 2;

        }

        System.out.printf("%.3f", area);
    }
}