import textio.TextIO;

public class Exercise2_4 {
    public static void main(String[] args) {
        System.out.print("How many 50 cent pieces do you have? ");
        int cents_50 = TextIO.getlnInt();

        System.out.print("How many 20 cent pieces do you have? ");
        int cents_20 = TextIO.getlnInt();

        System.out.print("How many 10 cent pieces do you have? ");
        int cents_10 = TextIO.getlnInt();

        System.out.print("How many 5 cent pieces do you have? ");
        int cents_5 = TextIO.getlnInt();

        double total = cents_50 * 0.5 + cents_20 * 0.2 + cents_10 * 0.1 + cents_5 * 0.05;
        System.out.print("You have $" + total + "!");
    }
}
