package exercises;

import utils.InputUtils;

public class Task_03_Deposit_Calculator {
    public static void main(String[] args) {
        double depositAmount = InputUtils.readDouble();
        int depositTerm = InputUtils.readInteger();
        double annualInterestRate = InputUtils.readDouble();

        double finalDepositAmount = calculateSum(depositAmount, depositTerm, annualInterestRate);
        System.out.println(finalDepositAmount);
    }

    private static double calculateSum(double depositAmount, int depositTerm, double annualInterestRate) {
        return depositAmount + depositTerm * ((depositAmount * annualInterestRate / 100) / 12);
    }
}