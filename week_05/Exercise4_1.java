public class Exercise4_1 {
    public static void main(String[] args) {
        printCapitalized("Now is the time to act!");
    }

    public static void printCapitalized(String sentence) {
        char[] letters = sentence.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            if (Character.isLetter(letters[i]) && i > 0 ? !Character.isLetter(letters[i-1]) : false) {
                letters[i] = Character.toUpperCase(letters[i]);
            }
        }

        System.out.println(new String(letters));
    }
}
