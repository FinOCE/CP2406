import textio.TextIO;

public class Exercise4_8 {
    private static final int QUESTION_COUNT = 10;
    private static final int POINTS_PER_CORRECT = 10;

    private static int[] number1 = new int[QUESTION_COUNT];
    private static int[] number2 = new int[QUESTION_COUNT];
    private static int[] answers = new int[QUESTION_COUNT];

    public static void main(String[] args) {
        create();
        administer();
        grade();
    }

    private static void create() {
        for (int i = 0; i < QUESTION_COUNT; i++) {
            number1[i] = (int)(Math.random()*50);
            number2[i] = (int)(Math.random()*50);
        }
    }

    private static void administer() {
        System.out.println("Answer the following questions...");

        for (int i = 0; i < QUESTION_COUNT; i++) {
            System.out.printf("%d + %d = ", number1[i], number2[i]);
            answers[i] = TextIO.getlnInt();
        }
    }

    private static void grade() {
        System.out.println("\nResults...");

        int correctAnswers = 0;

        for (int i = 0; i < QUESTION_COUNT; i++) {
            int answer = number1[i] + number2[i];
            
            if (answer == answers[i]) correctAnswers++;

            String grade = answer == answers[i] ? "Correct!" : "Incorrect!";
            String correction = answer == answers[i] ? "" : ("Correct answer: " + answer);

            System.out.printf("%2d + %2d = %2d     %s %s\n", number1[i], number2[i], answers[i], grade, correction);
        }

        System.out.printf("\nTotal score: %d/%d", correctAnswers*POINTS_PER_CORRECT, QUESTION_COUNT*POINTS_PER_CORRECT);
    }
}
