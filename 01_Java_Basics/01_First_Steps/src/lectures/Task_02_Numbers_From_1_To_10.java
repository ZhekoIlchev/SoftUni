package lectures;

public class Task_02_Numbers_From_1_To_10 {
    public static void main(String[] args) {
        System.out.println(1);
        System.out.println(2);
        System.out.println(3);
        System.out.println(4);
        System.out.println(5);
        System.out.println(6);
        System.out.println(7);
        System.out.println(8);
        System.out.println(9);
        System.out.println(10);

        System.out.println();

        printNumbers(10);
    }

    public static void printNumbers(int endNumber) {
        for (int i = 1; i < endNumber; i++) {
            System.out.println(i);
        }
    }
}