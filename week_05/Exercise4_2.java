import textio.TextIO;

public class Exercise4_2 {
    public static void main(String[] args) {
        System.out.print("Enter hexadecimal number: ");
        String hexString = TextIO.getln();

        int value = 0;
        for (int i = 0; i < hexString.length(); i++) {
            int decimalValue = hexValue(hexString.charAt(i));
            if (decimalValue == -1) {
                System.out.print("Error: Invalid character!");
                return;
            }

            value = value*16 + decimalValue;
        }

        System.out.printf("The decimal representation of %s is %d.", hexString, value);
    }

    public static int hexValue(char ch) {
        /*
            Would make more sense to use the ASCII index of the char but task specifically
            required that a switch case be used so this is the result.
        */
        switch (Character.toLowerCase(ch)) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return Integer.parseInt(String.valueOf(ch));
            case 'a':
                return 10;
            case 'b':
                return 11;
            case 'c':
                return 12;
            case 'd':
                return 13;
            case 'e':
                return 14;
            case 'f':
                return 15;
            default:
                return -1;
        }
    }
}
