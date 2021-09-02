import textio.TextIO;

public class Exercise5_7 {
    public interface IntQuestion {
        public String getQuestion();
        public int getCorrectAnswer();
    }

    public static class AdditionQuestion implements IntQuestion {
        private int a, b;

        public AdditionQuestion() {
            a = (int)(Math.random() * 50 + 1);
            b = (int)(Math.random() * 50);
        }

        public String getQuestion() {
            return "What is " + a + " + " + b + " ?";
        }

        public int getCorrectAnswer() {
            return a + b;
        }
    }

    public static class SubtractionQuestion implements IntQuestion {
        private int a, b;

        public SubtractionQuestion() {
            a = (int)(Math.random() * 50 + 1);
            b = (int)(Math.random() * 50);

            if (a - b < 0) {
                int temp = a;
                a = b;
                b = temp;
            }
        }

        public String getQuestion() {
            return "What is " + a + " - " + b + " ?";
        }

        public int getCorrectAnswer() {
            return a - b;
        }
    }

    private static final int QUESTION_COUNT = 10;
    private static final int POINTS_PER_CORRECT = 10;

    private static IntQuestion[] questions = new IntQuestion[10];
    private static int[] answers = new int[QUESTION_COUNT];

    public static void main(String[] args) {
        create();
        administer();
        grade();
    }

    private static void create() {
        for (int i = 0; i < QUESTION_COUNT; i++) {
            switch ((int)(Math.random()*2)) {
                case 0:
                    questions[i] = new AdditionQuestion();
                    break;
                case 1:
                    questions[i] = new SubtractionQuestion();
                    break;
            }
            
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
