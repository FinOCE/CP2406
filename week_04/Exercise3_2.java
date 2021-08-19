public class Exercise3_2 {
    public static void main(String[] args) {
        int maxNum = 10000;

        int mostDivisorsCount = 1;
        int mostDivisorsNum = 1;
        for (int i = 2; i <= maxNum; i++) {
            int divisorCount = 0;

            for (int j = 1; j <= i/2; j++) {
                if (i % j == 0) divisorCount++;
            }

            if (divisorCount > mostDivisorsCount) {
                mostDivisorsCount = divisorCount;
                mostDivisorsNum = i;
            }
        }

        System.out.printf("%d has %d divisors, which is the most out of all real numbers up to %d", mostDivisorsNum, mostDivisorsCount, maxNum);
    }
}
