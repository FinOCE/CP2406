public class Exercise3_7 {
    public static void main(String[] args) {
        int Q3_1 = Exercise3_7.Q3_1();
        int Q3_2 = Exercise3_7.Q3_2();
        int Q3_3 = Exercise3_7.Q3_3();

        System.out.printf("(Q1) It took %d people to find 3 people with the same birthday.\n", Q3_1);
        System.out.printf("(Q2) A random group of 365 people had %d unique birthdays.\n", Q3_2);
        System.out.printf("(Q3) It took %d people to find a group with a birthday on every day.\n", Q3_3);
    }

    private static int Q3_1() {
        int totalBirthdays = 0;
        int[] birthdays = new int[365];

        while (true) {
            int birthday = (int)(Math.random() * 365);
            birthdays[birthday]++;
            totalBirthdays++;

            if (birthdays[birthday] == 3)
                break;
        }

        return totalBirthdays;
    }

    private static int Q3_2() {
        boolean[] birthdays = new boolean[365];
        int uniqueBirthdays = 0;
        
        for (int i = 0; i < 365; i++) {
            int day = (int)(Math.random() * 365);
            if (!birthdays[day]) {
                uniqueBirthdays++;
                birthdays[day] = true;
            }
        }

        return uniqueBirthdays;
    }

    private static int Q3_3() {
        int peopleCounted = 0;
        int birthdaysRemaining = 365;
        boolean[] birthdays = new boolean[365];

        while (birthdaysRemaining > 0) {
            peopleCounted++;

            int day = (int)(Math.random() * 365);
            if (!birthdays[day]) {
                birthdaysRemaining--;
                birthdays[day] = true;
            }
        }

        return peopleCounted;
    }
}
