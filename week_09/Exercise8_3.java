import java.util.Scanner;

public class Exercise8_3 {
    private static class RomanNumeral {
        private static final int[] NUMBERS = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        private static final String[] LETTERS = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        private int integer;
        private String romanNumeral;

        /**
         * Initialise a RomanNumeral from a String
         * @param value A String representing a roman numeral
         * @throws IllegalArgumentException If the provided String is not a valid roman numeral or is out of the bounds of the class
         */
        public RomanNumeral(String value) throws IllegalArgumentException {
            integer = toInteger(value);
            romanNumeral = value;
        }

        /**
         * Initialise a RomanNumeral from an int
         * @param value An int representing a number
         * @throws IllegalArgumentException If the provided int is out of the bounds of the class
         */
        public RomanNumeral(int value) throws IllegalArgumentException {
            integer = value;
            romanNumeral = toRomanNumeral(value);
        }

        /**
         * Converts an int to a String representing a roman numeral
         * @param value An int to convert
         * @return A String representing a roman numeral
         * @throws IllegalArgumentException If the provided int is out of the bounds of the class
         */
        private String toRomanNumeral(int value) throws IllegalArgumentException {
            if ((value > 3999) || (value <= 0))
                throw new IllegalArgumentException();

            int total = value;
            String response = new String();
            int i = 0;
            
            while (i < NUMBERS.length) {
                while (NUMBERS[i] <= total) {
                    response += LETTERS[i];
                    total -= NUMBERS[i];
                }

                i++;
            }

            return response;
        }

        /**
         * Converts an String representing a roman numeral to an int
         * @param value An String to convert
         * @return An int from the roman numeral
         * @throws IllegalArgumentException If the provided String is not a valid roman numeral or is out of the bounds of the class
         */
        private int toInteger(String value) throws IllegalArgumentException {
            int total = 0;
            int upTo = 0;

            while (value.length() > 0) {
                boolean foundLetter = false;

                letterLoop: for (int i = upTo; i < LETTERS.length; i++) {
                    if (value.startsWith(LETTERS[i])) {
                        total += NUMBERS[i];
                        upTo = i; // There cannot be multiple instances of a value from letters, so can loop from that point
                        foundLetter = true;
                        value = value.substring(LETTERS[i].length());
                        break letterLoop;
                    }
                }

                if (!foundLetter)
                    throw new IllegalArgumentException();
            }

            return total;
        }

        /**
         * Get the roman numeral as a String
         * @return The roman numeral as a String
         */
        public String toRomanNumeral() {
            return romanNumeral;
        }

        /**
         * Get the roman numeral as an int
         * @return The roman numeral as an int
         */
        public int toInteger() {
            return integer;
        }
    }

    public static void main(String[] args) {
        System.out.println("Convert integers to roman numerals and vice versa");
        System.out.println("Enter any roman numeral or integer between 1 and 3999 (or 0 to exit):");

        var scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String rawstr = scanner.next().trim().toUpperCase();

            try {
                if (Character.isDigit(rawstr.charAt(0))) {
                    // First character was a digit
                    int rawint = Integer.parseInt(rawstr);

                    if (rawint == 0)
                        break; // Exit if user enters "0"
                    
                    String value = new RomanNumeral(rawint).toRomanNumeral();
                    System.out.printf("  = %s\n", value);
                } else {
                    // First character was not a digit
                    int value = new RomanNumeral(rawstr).toInteger();
                    System.out.printf("  = %d\n", value);
                }
            } catch (NumberFormatException e) {
                System.out.println("The provided value was not a valid integer!");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println("Value must be equivalent to between 1 and 3999!");
                continue;
            }
        }

        System.out.println("That's enough roman numerals for now.");
        scanner.close();
    }
}
