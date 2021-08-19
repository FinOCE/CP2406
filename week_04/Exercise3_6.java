public class Exercise3_6 {
    public static void main(String[] args) {
        int maxNum = 10000;

        int[] divisors = new int[maxNum + 1];

        int mostDivisors = 1;
        divisors[1] = 1;

        for (int i = 2; i <= maxNum; i++) {
            int divisorCount = 1; // Loop below only goes to half way, so adding 1 to count the number itself

            for (int j = 1; j <= i/2; j++) {
                if (i % j == 0) divisorCount++;
            }

            divisors[i] = divisorCount;

            if (divisorCount > mostDivisors)
                mostDivisors = divisorCount;
        }

        System.out.println("Among integers between 1 and " + maxNum);
        System.out.println("The maximum number of divisors was " + mostDivisors);
        System.out.println("Numbers with that many divisors include:");
        for (int i = 1; i < maxNum; i++) {
            if (divisors[i] == mostDivisors)
                System.out.println("    " + i);
        }
    }
}
