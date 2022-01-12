package Lab_Problem_05_StackOfStrings;

public class Main {
    public static void main(String[] args) {

        StackOfStrings stack = new StackOfStrings();
        stack.push("One");
        stack.push("Two");
        stack.push("Three");

        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
