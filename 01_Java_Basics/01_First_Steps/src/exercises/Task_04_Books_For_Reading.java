package exercises;

import utils.InputUtils;

public class Task_04_Books_For_Reading {
    public static void main(String[] args) {
        int allPages = InputUtils.readInteger();
        int pagesPerHour = InputUtils.readInteger();
        int days = InputUtils.readInteger();

        int hoursPerDay = allPages / pagesPerHour / days;
        System.out.println(hoursPerDay);
    }
}