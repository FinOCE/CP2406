public class Exercise5_1 {
    public static final int TOTAL = 2;
    public static void main(String[] args) {
        PairOfDice dice = new PairOfDice();

        int i = 0;
        do {
            i++;
            dice.roll();
            System.out.println("Roll " + i + ": " + dice.toString() + " (" + dice.getTotal() + ")");
        } while (!dice.totalEquals(TOTAL));

        System.out.println("It took " + i + " rolls to roll the dice with a combined total of " + TOTAL);
    }
}
