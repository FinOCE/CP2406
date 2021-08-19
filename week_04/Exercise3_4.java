import textio.TextIO;

public class Exercise3_4 {
    public static void main(String[] args) {
        // Read file
        String file = "sales.dat";
        try {
            TextIO.readFile(file);
        } catch (Exception e) {
            System.out.println("Could not open file!");
        }

        // Count number of invalid cities and sum sales
        int invalidCities = 0;
        double salesTotal = 0;

        while (!TextIO.eof()) {
            String line = TextIO.getln();

            try {
                salesTotal += Double.parseDouble(line.split(":")[1].trim());
            } catch (Exception e) {
                invalidCities++;
            }
        }

        // Write results to console
        System.out.printf("Total sales amount: %.2f", salesTotal);
        System.out.printf("Number of cities with no sales data: %d\n", invalidCities);
    }
}
