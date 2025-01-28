package lectures;

import utils.InputUtils;

public class Task_06_Concatenate_Data {
    public static void main(String[] args) {
        String firstName = InputUtils.readInput();
        String lastName = InputUtils.readInput();
        int age = InputUtils.readInteger();
        String town = InputUtils.readInput();

        String personInformation = String.format("Your are %s %s, a %d-years old person from %s.",
                firstName, lastName, age, town);

        System.out.println(personInformation);
    }
}