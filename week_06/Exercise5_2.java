import textio.TextIO;

public class Exercise5_2 {
    public static void main(String[] args) {
        StatCalc calc = new StatCalc();

        while (true) {
            System.out.print("Enter value: ");
            double value = TextIO.getlnDouble();
            if (value == 0) break;
            calc.enter(value);
        }

        System.out.println("Count: " + calc.getCount());
        System.out.println("Sum:   " + calc.getSum());
        System.out.println("Mean:  " + calc.getMean());
        System.out.println("SD:    " + calc.getStandardDeviation());
        System.out.println("Max:   " + calc.getMax());
        System.out.println("Min:   " + calc.getMin());
    }
}
