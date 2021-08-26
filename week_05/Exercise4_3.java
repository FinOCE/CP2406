public class Exercise4_3 {
    public static void main(String[] args) {
        int rolls = rollUntil(2);
        System.out.printf("It took %d rolls to roll snake eyes.", rolls);
    }

    public static int rollUntil(int total) {
        if (total > 12 || total < 2) throw new IllegalArgumentException("Impossible total");

        int roll1, roll2;
        int rolls = 0;

        do {
            roll1 = (int)(Math.random()*6 + 1);
            roll2 = (int)(Math.random()*6 + 1);
            rolls++;
        } while (roll1 + roll2 != total);

        return rolls;
    }
}
