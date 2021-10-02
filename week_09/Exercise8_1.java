import textio.TextIO;

public class Exercise8_1 {
    public static void main(String[] args) {
        System.out.println("Find the greater value of the quadratic expression using these values:");

        do {
            double[] values = new double[3];

            for (int i = 0; i < 3; i++) {
                System.out.printf("%s = ", (char)(65 + i)); // Using ASCII charcodes for A, B, C
                values[i] = TextIO.getlnDouble();
            }

            try {
                double answer = root(values[0], values[1], values[2]);
                System.out.printf("Your answer is %f\n", answer);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Do you want to do another calculation?");
            if (!TextIO.getlnBoolean()) break;
        } while (true);
    }

    public static double root(double A, double B, double C) throws IllegalArgumentException {
        if (A == 0)
            throw new IllegalArgumentException("A can't be zero.");

        double disc = B*B - 4*A*C;
        if (disc < 0)
            throw new IllegalArgumentException("Discriminant < zero.");

        return (-B + Math.sqrt(disc)) / (2*A);
    }
}