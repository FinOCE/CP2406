import java.util.Scanner;

public class Exercise9_1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        do {
            System.out.println("Enter an integer greater than 0 (or 0 to exit)");

            int value = scanner.nextInt();
            if (value == 0)
                break;
            else if (value < 0) {
                System.out.println("Value must be greater than 0");
                continue;
            }

            System.out.printf("The factorial of %d is %d\n", value, factorial(value));
            System.out.printf("The fibonacci of %d is %d\n", value, fibonacci(value));
        } while (true);

        scanner.close();
    }

    private static int factorial(int value) {
        if (value == 0)
            return 1;

        return value * factorial(value - 1);
    }

    private static int fibonacci(int value) {
        if (value == 0 || value == 1)
            return 1;

        return fibonacci(value - 1) + fibonacci(value - 2);
    }
}