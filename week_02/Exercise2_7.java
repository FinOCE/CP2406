import textio.TextIO;

public class Exercise2_7 {
    public static void main(String[] args) {
        TextIO.readFile("testdata.txt");
        
        String name = TextIO.getln();
        int score1 = TextIO.getlnInt();
        int score2 = TextIO.getlnInt();
        int score3 = TextIO.getlnInt();

        int average = (score1 + score2 + score3)/3;
        System.out.println(name + " scored an average of " + average);
    }
}
