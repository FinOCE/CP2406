import textio.TextIO;
import java.util.TreeMap;
import java.util.TreeSet;

public class Exercise10_6 {
    private static TreeMap<String, TreeSet<Integer>> concordance = new TreeMap<String, TreeSet<Integer>>();

    public static void main(String[] args) {
        try {
            if (TextIO.readUserSelectedFile() == false) {
                System.out.println("No file was selected...");
                System.exit(1);
            }

            int lineNum = 1;

            while (true) {
                var ch = TextIO.peek();

                while (ch != TextIO.EOF && !Character.isLetter(ch)) {
                    TextIO.getAnyChar();

                    if (ch == '\n')
                        lineNum++;

                    ch = TextIO.peek();
                }

                if (ch == TextIO.EOF)
                    break;

                var word = readNextWord().toLowerCase();
                if (word.length() > 2 && !word.equalsIgnoreCase("the")) {
                    addReference(word, lineNum);
                }
            }

            int wordCount = concordance.size();
            System.out.printf("There were %d unique words in the file\n", wordCount);

            if (wordCount == 0) {
                System.out.println("Because there were no words, no need to save data");
                System.exit(0);
            }

            TextIO.writeUserSelectedFile();

            printConcordance();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private static void printConcordance() {

        for (var entry : concordance.entrySet()) {
            var term = entry.getKey();
            var pageSet = entry.getValue();

            TextIO.put(term + " ");

            for (int page : pageSet) {
                TextIO.put(page + " ");
            }

            TextIO.putln();
        }
    }

    private static void addReference(String word, int lineNum) {
        var references = concordance.get(word);

        if (references == null) {
            var firstRef = new TreeSet<Integer>();
            firstRef.add(lineNum);
            concordance.put(word, firstRef);
        } else {
            references.add(lineNum);
        }
    }

    private static String readNextWord() {
        char ch = TextIO.peek();

        while (ch != TextIO.EOF && !Character.isLetter(ch)) {
            TextIO.getAnyChar();
            ch = TextIO.peek();
        }

        if (ch == TextIO.EOF)
            return null;

        String word = "";
        while (true) {
            word += TextIO.getAnyChar();

            ch = TextIO.peek();
            if (ch == '\'') {
                TextIO.getAnyChar();
                ch = TextIO.peek();

                if (Character.isLetter(ch)) {
                    word += "\'" + TextIO.getAnyChar();
                    ch = TextIO.peek();
                } else
                    break;
            }

            if (!Character.isLetter(ch))
                break;
        }

        return word;
    }
}
