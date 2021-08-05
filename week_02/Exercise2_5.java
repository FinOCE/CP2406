import textio.TextIO;

public class Exercise2_5 {
    public static void main(String[] args) {
        System.out.print("How many eggs do you have? ");
        int eggCount = TextIO.getlnInt();

        int grossOfEggs = eggCount / 144;
        eggCount -= grossOfEggs * 144;

        int dozensOfEggs = eggCount / 12;
        int remainingEggs = eggCount % 12;

        System.out.println("Your number of eggs is " + grossOfEggs + " gross, " + dozensOfEggs + " dozen, and " + remainingEggs);
    }
}
