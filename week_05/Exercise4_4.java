public class Exercise4_4 {
    public static final int ATTEMPTS = 10000;
    
    public static void main(String[] args) {
        System.out.println("Total On Dice     Average Number of Rolls");
        System.out.println("-------------     -----------------------");

        for (int i = 2; i <= 12; i++) {
            System.out.printf("%10d%22.4f\n", i, attemptRollsFor(i));
        }
    }

    public static double attemptRollsFor(int value) {
        int total = 0;

        for (int i = 0; i < ATTEMPTS; i++) {
            total += rollUntil(value);
        }

        return (double)total/ATTEMPTS;
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
