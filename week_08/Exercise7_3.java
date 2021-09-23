import java.util.Arrays;

public class Exercise7_3 {
    private static final int LENGTH = 100000;

    public static void main(String[] args) {
        double[] numbers1 = createRandomNumbers(LENGTH);
        double[] numbers2 = numbers1.clone();

        String[] strings1 = createRandomStrings(LENGTH);
        String[] strings2 = strings1.clone();

        long startTime;
        long endTime;

        System.out.println("--- Numbers ---");

        System.out.println("selectionSort:");
        startTime = System.nanoTime();
        selectionSort(numbers1);
        endTime = System.nanoTime();
        System.out.printf("It took %.3f seconds to sort %d numbers using selectionSort\n", (endTime - startTime) / 1e9, LENGTH);

        System.out.println("Arrays.sort():");
        startTime = System.nanoTime();
        Arrays.sort(numbers2);
        endTime = System.nanoTime();
        System.out.printf("It took %.3f seconds to sort %d numbers using Arrays.sort()\n", (endTime - startTime) / 1e9, LENGTH);

        System.out.println("--- Strings ---");

        System.out.println("selectionSort:");
        startTime = System.nanoTime();
        selectionSort(strings1);
        endTime = System.nanoTime();
        System.out.printf("It took %.3f seconds to sort %d strings using selectionSort\n", (endTime - startTime) / 1e9, LENGTH);

        System.out.println("Arrays.sort():");
        startTime = System.nanoTime();
        Arrays.sort(strings2);
        endTime = System.nanoTime();
        System.out.printf("It took %.3f seconds to sort %d strings using Arrays.sort()\n", (endTime - startTime) / 1e9, LENGTH);

    }

    private static double[] createRandomNumbers(int count) {
        double[] numbers = new double[count];

        for (int i = 0; i < count; i++)
            numbers[i] = Math.random() * 2 - 1; // Generate [-1, 1)

        return numbers;
    }

    private static String createRandomString(int length) {
        char[] characters = new char[length];

        for (int i = 0; i < length; i++)
            characters[i] = (char)('a' + (int)(Math.random()*26)); // Generate a random character from a to z
        
        return characters.toString();
    }

    private static String[] createRandomStrings(int count) {
        String[] strings = new String[count];

        for (int i = 0; i < count; i++)
            strings[i] = createRandomString((int)(Math.random()*9 + 1)); // Generate strings of 1-10 characters

        return strings;
    }

    private static double[] selectionSort(double[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            int maxIndex = 0;

            for (int j = 1; j <= i; j++)
                if (numbers[j] > numbers[maxIndex])
                    maxIndex = j;

            double temp = numbers[i];
            numbers[i] = numbers[maxIndex];
            numbers[maxIndex] = temp;
        }

        return numbers;
    }

    private static String[] selectionSort(String[] strings) {
        for (int i = strings.length - 1; i > 0; i--) {
            int maxIndex = 0;

            for (int j = 1; j <= i; j++)
                if (strings[j].compareTo(strings[maxIndex]) > 0)
                    maxIndex = j;

            String temp = strings[i];
            strings[i] = strings[maxIndex];
            strings[maxIndex] = temp;
        }

        return strings;
    }
}
