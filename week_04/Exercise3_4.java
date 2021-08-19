import textio.TextIO;

public class Exercise3_4 {
    public static void main(String[] args) {
        // Get sentence
        System.out.print("Enter a sentence: ");
        String sentence = TextIO.getln();

        System.out.println();

        // Loop over each character
        boolean lastWasLetter = false;

        for (int i = 0; i < sentence.length(); i++) {
            char character = sentence.charAt(i);

            if (Character.isLetter(character) || character == '\'') {
                System.out.print(character);
                lastWasLetter = true;
            } else {
                if (lastWasLetter) {
                    System.out.println();
                    lastWasLetter = false;
                }
            }
        }
    }
}
