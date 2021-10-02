import java.math.BigInteger;
import java.util.Scanner;

public class Exercise8_2 {
    private static final BigInteger ONE = new BigInteger("1");
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        do {
            System.out.print("Enter value: ");
            String input = scanner.nextLine().trim();
            if (input.length() == 0) break;

            try {
                var value = new BigInteger(input);
                if (value.signum() == 1) {
                    printSequence(value);
                } else {
                    System.out.println("Starting value must be greater than zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("The provided value is not a valid number.");
            }
        } while (true);

        System.out.println("That's enough 3N+1 for now.");

        scanner.close();
    }

    private static void printSequence(BigInteger value) {
        assert value != null && value.signum() == 1 : "Illegal paramater value";

        int i = 1;

        while (!value.equals(ONE)) {
            if (!value.testBit(0)) {
                // value is even
                value = value.divide(TWO);
            } else {
                // value is odd
                value = value.multiply(THREE);
                value = value.add(ONE);
            }
            
            System.out.println(value.toString());
            i++;
        }

        System.out.printf("There were %d values in the sequence.\n", i);
    }
}