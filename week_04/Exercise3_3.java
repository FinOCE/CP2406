import textio.TextIO;

public class Exercise3_3 {
    public static void main(String[] args) {
        String[] operators = { "+", "-", "*", "/" };

        while (true) {
            // Get expression
            System.out.print("Enter your simple expression: ");
            String expression = TextIO.getln().replace(" ", "");
            if (expression.matches("0")) break;

            // Determine operator
            String operator = null;

            for (int i = 0; i < operators.length; i++) {
                if (expression.contains(operators[i]))
                    operator = operators[i];
            }

            if (operator == null) {
                System.out.println("The expression did not contain a valid operator, please try again.");
                continue;
            }

            // Parse values
            double value1 = Double.parseDouble(expression.split("\\" + operator)[0]);
            double value2 = Double.parseDouble(expression.split("\\" + operator)[1]);
            double result;

            // Calculate result
            switch (operator) {
                case "+":
                    result = value1 + value2;
                    break;
                case "-":
                    result = value1 - value2;
                    break;
                case "*":
                    result = value1 * value2;
                    break;
                case "/":
                    result = value1 / value2;
                    break;
                default:
                    continue; // This should never occur but added to ensure result is initialised
            }
            
            System.out.println(result);
        }
    }
}
