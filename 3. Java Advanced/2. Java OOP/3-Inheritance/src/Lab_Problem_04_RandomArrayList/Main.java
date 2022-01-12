package Lab_Problem_04_RandomArrayList;

public class Main {
    public static void main(String[] args) {
        RandomArrayList list = new RandomArrayList();

        list.add("Anna Karenina");
        list.add("The Great Gatsby");
        list.add("The Count of Monte Cristo");

        System.out.println(list.getRandomElement());
    }
}
