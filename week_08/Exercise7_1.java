import java.util.ArrayList;

public class Exercise7_1 {
    public static void main(String[] args) {
        System.out.println(createRandomList(5, 3)); // ArrayList with 3 random unique values between 1 and 5
        System.out.println(createRandomList(10, 10)); // ArrayList with 10 random unique values between 1 and 10
        System.out.println(createRandomList(2, 3)); // Will throw IllegalArgumentException
    }

    public static ArrayList<Integer> createRandomList(int m, int n) {
        if (n > m) throw new IllegalArgumentException("Cannot generate a unique list with more values than the maximum");

        var list = new ArrayList<Integer>();

        while (list.size() < n) {
            int value = (int)(Math.random() * m + 1);
            if (list.indexOf(value) == -1) list.add(value);
        }

        return list;
    }
}