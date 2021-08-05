import textio.TextIO;

public class WidgetCalculator {
    public static void main(String[] args) {
        System.out.print("Number of widgets: ");
        int numberOfWidgets = TextIO.getlnInt();

        System.out.print("Cost per widget: $");
        double costPerWidget = TextIO.getlnDouble();

        double total = numberOfWidgets * costPerWidget;

        System.out.printf("Total cost: $%1.2f", total);
    }
}
