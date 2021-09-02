public class PairOfDice {
    private int die1;
    private int die2;
    
    public PairOfDice() {
        roll();
    }

    public void roll() {
        die1 = (int)(Math.random()*6) + 1;
        die2 = (int)(Math.random()*6) + 1;
    }

    public int getDie1() {
        return die1;
    }

    public int getDie2() {
        return die2;
    }

    public String toString() {
        return die1 + " and " + die2;
    }

    public int getTotal() {
        return die1 + die2;
    }

    public boolean totalEquals(int value) {
        return getTotal() == value;
    }
}
