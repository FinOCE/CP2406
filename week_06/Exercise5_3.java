public class Exercise5_3 {
    public static final int NUMBER_OF_EXPERIMENTS = 10000;

    public static void main(String[] args) {
        System.out.println("Roll Value   Average Rolls   Standard Deviation   Maximum Rolls");
        System.out.println("----------   -------------   ------------------   -------------");

        for (int i = 2; i <= 12; i++) {
            StatCalc calc = experimentForRoll(i);

            System.out.printf("%10d   ", i);
            System.out.printf("%13.4f   ", calc.getMean());
            System.out.printf("%18.4f   ", calc.getStandardDeviation());
            System.out.printf("%13.0f\n", calc.getMax());
        }
    }

    private static StatCalc experimentForRoll(int total) {
        StatCalc calc = new StatCalc();

        for (int i = 0; i < NUMBER_OF_EXPERIMENTS; i++) {
            int rolls = rollFor(total);
            calc.enter(rolls);
        }

        return calc;
    }

    private static int rollFor(int total) {
        PairOfDice dice = new PairOfDice();
        int rolls = 0;

        do {
            rolls++;
            dice.roll();
        } while (!dice.totalEquals(total));

        return rolls;
    }
}
