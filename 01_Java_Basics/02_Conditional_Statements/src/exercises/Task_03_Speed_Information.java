package exercises;

import utils.InputUtil;

public class Task_03_Speed_Information {
    public static void main(String[] args) {
        double speed = InputUtil.readDouble();

        if (speed <= 10) {
            System.out.println("slow");
        } else if (speed <= 50) {
            System.out.println("average");
        } else if (speed <= 150) {
            System.out.println("fast");
        } else if (speed <= 1000) {
            System.out.println("ultra fast");
        } else {
            System.out.println("extremely fast");
        }
    }
}