import textio.TextIO;
import expr.Expr;

public class Exercise8_4 {
    public static void main(String[] args) {
        System.out.println("Calculate expressions using \"x\" as the variable.");

        while (true) {
            System.out.print("Expression: ");
            String expression = TextIO.getln();

            if (expression.length() == 0)
                break;

            Expr expr = new Expr(expression);

            while (true) {
                System.out.print("Value: ");
                String raw = TextIO.getln().trim();
                double value;

                if (raw.length() == 0)
                    break;
                
                try {
                    value = Double.parseDouble(raw);
                } catch (NumberFormatException e) {
                    System.out.println("The provided value was not valid.");
                    continue;
                }

                double result = expr.value(value);
                
                if (Double.isNaN(result)) {
                    System.out.println("There is no solution at this value (NaN)");
                    continue;
                }

                System.out.printf("f(%s) = %s\n", raw, result);
            }
        }

        System.out.println("That's enough maths for now.");
    }
}
