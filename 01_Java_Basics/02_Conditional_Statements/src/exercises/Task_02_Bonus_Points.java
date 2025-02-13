package exercises;

import utils.InputUtil;

public class Task_02_Bonus_Points {
    public static void main(String[] args) {
        double points = InputUtil.readDouble();
        double bonusPoints = 0;

        if (points <= 100) {
            bonusPoints += 5;
        } else if (points <= 1000) {
            bonusPoints += points * 20 / 100;
        } else {
            bonusPoints = points * 10 / 100;
        }

        if (points % 2 == 0) {
            bonusPoints += 1;
        } else {
            if (points % 5 == 0) {
                bonusPoints += 2;
            }
        }

        System.out.println(bonusPoints);
        System.out.println(points + bonusPoints);
    }
}