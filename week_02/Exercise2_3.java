import textio.TextIO;

public class Exercise2_3 {
    public static void main(String[] args) {
        System.out.print("What is your name? ");
        String name = TextIO.getlnString();

        System.out.println("Hello, " + name.toUpperCase() + ", nice to meet you!");
    }
}
