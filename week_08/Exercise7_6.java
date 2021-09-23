import java.util.ArrayList;

import textio.TextIO;

public class Exercise7_6 {
    private class QuickSort {
        public static ArrayList<String> sort(ArrayList<String> arr, int length) {
            sort(arr, 0, length - 1);
            return arr;
        }
    
        private static void sort(ArrayList<String> arr, int low, int high) {
            if (low < high) {
                int pi = partition(arr, low, high);
    
                sort(arr, low, pi - 1);
                sort(arr, pi + 1, high);
            }
        }
    
        private static int partition(ArrayList<String> arr, int low, int high) {
            String pivot = arr.get(high);
            int i = low - 1;
    
            for (int j = low; j <= high; j++) {
                if (arr.get(j).compareTo(pivot) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
    
            swap(arr, i + 1, high);
    
            return i + 1;
        }
    
        private static void swap(ArrayList<String> arr, int i, int j) {
            String temp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, temp);
        }
    }

    public static void main(String[] args) {
        if (TextIO.readUserSelectedFile() == false) {
            System.out.println("No file selected.");
            System.exit(1);
        }

        var words = new ArrayList<String>();
        while (true) {
            String word = readNextWord();
            if (word == null) break;

            word = word.toLowerCase();
            if (words.indexOf(word) == -1) words.add(word);
        }

        int size = words.size();

        System.out.printf("%d unique words were in this file\n", size);
        if (size == 0) {
            System.out.println("Because there were no words, there is no need to save this data");
            System.exit(0);
        }

        QuickSort.sort(words, size);
        TextIO.writeUserSelectedFile();

        for (String word : words) TextIO.putln(word);
        System.exit(0);
    }

    private static String readNextWord() {
        char ch = TextIO.peek();

        while (ch != TextIO.EOF && ! Character.isLetter(ch)) {
           TextIO.getAnyChar();
           ch = TextIO.peek();
        }

        if (ch == TextIO.EOF) return null;

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
                } else break;
            }

            if (!Character.isLetter(ch)) break;
        }

        return word;
    }
}
