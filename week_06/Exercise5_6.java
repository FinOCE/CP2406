import textio.TextIO;

public class Exercise5_6 {
    private static final int QUESTION_COUNT = 10;
    private static final int POINTS_PER_CORRECT = 10;

    private static AdditionQuestion[] questions = new AdditionQuestion[10];
    private static int[] answers = new int[QUESTION_COUNT];

    public static void main(String[] args) {
        create();
        administer();
        grade();
    }

    private static void create() {
        for (int i = 0; i < QUESTION_COUNT; i++) {
            questions[i] = new AdditionQuestion();
        }
    }

    private static void administer() {
        System.out.println("Answer the following questions...");

        for (int i = 0; i < QUESTION_COUNT; i++) {
            System.out.print(questions[i].getQuestion() + " ");
            answers[i] = TextIO.getlnInt();
        }
    }

    private static void grade() {
        System.out.println("\nResults...");

        int correctAnswers = 0;

        for (int i = 0; i < QUESTION_COUNT; i++) {
            int answer = questions[i].getCorrectAnswer();
            if (answer == answers[i]) correctAnswers++;

            String grade = answer == answers[i] ? "Correct!" : "Incorrect!";
            String correction = answer == answers[i] ? "" : ("Correct answer: " + answer);

            System.out.printf("%-17s %-2s %s %s\n", questions[i].getQuestion(), answers[i], grade, correction);
        }

        System.out.printf("\nTotal score: %d/%d", correctAnswers*POINTS_PER_CORRECT, QUESTION_COUNT*POINTS_PER_CORRECT);
    }
}
