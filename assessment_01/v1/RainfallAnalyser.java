import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RainfallAnalyser {
    private static final String FILE = "MountSheridanStationCNS.csv";

    public static void main(String[] args) {
        // Fetch data from the CSV
        ArrayList<String[]> rainfallData;
        try {
            rainfallData = readCsvFile(FILE);
        } catch (IllegalArgumentException e) {
            System.out.println("The file you tried to read was not a CSV, program exiting");
            return;
        } catch (FileNotFoundException e) {
            System.out.println("The file you tried to read could not be found, program exiting");
            return;
        } catch (IOException e) {
            System.out.println("An I/O error occurred when attempting to read a line of the CSV, program exiting");
            return;
        }

        // Format the data from the CSV
        ArrayList<HashMap<String, String>> formattedData = formatRainfallData(rainfallData);
        if (formattedData.size() == 0) {
            System.out.println("The provided CSV resulted in 0 valid entries and therefore no analysis can be made, program exiting");
            return;
        }

        // Complete analysis for each month from the data
        /*
            While parseDouble can throw NumberFormatException, this has already been
            handled when the data was being formatted, meaning there is no way it can
            occur at this stage. Because of this, no handling is necessary.
        */
        var dates = forEachMonth(formattedData, (date, entries) -> date);

        var totalRainfall = forEachMonth(formattedData, (date, entries) -> {
            double total = 0;

            for (var entry : entries)
                total += Double.parseDouble(entry.get("rainfallAmount"));

            return total;
        });

        var minRainfall = forEachMonth(formattedData, (date, entries) -> {
            double min = 0;

            for (var entry : entries) {
                double value = Double.parseDouble(entry.get("rainfallAmount"));
                if (value < min) min = value;
            }

            return min;
        });

        var maxRainfall = forEachMonth(formattedData, (date, entries) -> {
            double max = 0;

            for (var entry : entries) {
                double value = Double.parseDouble(entry.get("rainfallAmount"));
                if (value > max) max = value;
            }

            return max;
        });

        System.out.printf("There are %d unique months in the dataset\n", dates.size());

        // Create ArrayList containing all analysed data
        var analysedData = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < dates.size(); i++) {
            var analysedEntry = new HashMap<String, String>();
            analysedEntry.put("date", dates.get(i));
            analysedEntry.put("total", Double.toString((Math.floor(totalRainfall.get(i)*100)/100))); // Round total to 2 decimal places (to prevent floating point inaccuracies)
            analysedEntry.put("min", minRainfall.get(i).toString());
            analysedEntry.put("max", maxRainfall.get(i).toString());
            analysedData.add(analysedEntry);
        }

        // Save data to a CSV
        String filename = FILE.substring(0, FILE.lastIndexOf(".")) + "_Analysed"; // Make filename the same as the input with "_Analysed" appended to the end
        String filetype = FILE.substring(FILE.lastIndexOf(".")); // Matches exact filetype of original e.g.: .csv, .CSV, .CsV, etc.
        String location = filename + filetype;
        try {
            writeCsvFile(location, analysedData);
        } catch (IOException e) {
            System.out.println("An I/O error occurred when attempting to save the data to a CSV");
            return;
        }

        System.out.printf("Analysis is complete, and the data is provided in %s\n", location);
    }

    /**
     * Fetch data from a CSV as a string array
     * @param location The file location of the CSV
     * @return An ArrayList of data from each row as a string array
     * @throws IllegalArgumentException Occurs when the given location is not a CSV file
     * @throws FileNotFoundException Occurs when the given file location is not valid
     * @throws IOException Occurs when the BufferReader fails to read a line of the CSV
     */
    private static ArrayList<String[]> readCsvFile(String location) throws IllegalArgumentException, FileNotFoundException, IOException {
        if (!location.toLowerCase().endsWith(".csv"))
            throw new IllegalArgumentException(); // Only allow CSV files to be read
        
        var fr = new FileReader(location);
        var br = new BufferedReader(fr);

        var data = new ArrayList<String[]>();

        while (true) {
            String line = br.readLine();
            if (line == null) break;

            data.add(line.split(","));
        }

        br.close();
        return data;
    }

    /**
     * Validate an integer
     * @param value A string potentially representing an integer
     * @return A valid string that can be parsed to an integer
     * @throws NumberFormatException Occurs when the provided value is not a valid integer
     */
    private static String validateInteger(String value) throws NumberFormatException {
        value = value.isEmpty() ? "0" : value;
        return Integer.toString(Integer.parseInt(value));
    }

    /**
     * Validate a double
     * @param value A string potentially representing a double
     * @return A valid string that can be parsed to a double
     * @throws NumberFormatException Occurs when the provided value is not a valid double
     */
    private static String validateDouble(String value) throws NumberFormatException {
        value = value.isEmpty() ? "0" : value;
        return Double.toString(Double.parseDouble(value));
    }

    /**
     * Validate a boolean
     * @param value A string potentially representing a boolean as a "Y" or "y"
     * @return A valid string that can be parsed to a boolean
     * @throws NumberFormatException Occurs when the provided value is not "Y" or "y"
     */
    private static String validateBoolean(String value) {
        return Boolean.toString(value.equalsIgnoreCase("y"));
    }

    /**
     * Format and validate rainfall data
     * @param oldData An ArrayList of String arrays with rainfallData
     * @return A validated String Hashmap ArrayList
     */
    private static ArrayList<HashMap<String, String>> formatRainfallData(ArrayList<String[]> oldData) {
        var newData = new ArrayList<HashMap<String, String>>();

        // Keep track of different exceptions thrown over the dataset
        int majorNumberFormatExceptions = 0;
        int majorIndexOutOfBoundsExceptions = 0;
        int undefinedRainfallAmountExceptions = 0;
        int minorIndexOutOfBoundsExceptions = 0;
        int minorMissingValuesExceptions = 0;

        for (int i = 1; i < oldData.size(); i++) { // Start at 1 to ignore column names
            var oldEntry = oldData.get(i);
            var newEntry = new HashMap<String, String>();
            /*
                Integer, Double, and Boolean are being parsed then converted back to a string
                so that they are checked to be valid and caught if not, then able to be put
                into the String HashMap knowing that parsing the values later will not cause
                issues.
            */
            try {
                // Required values in data
                /*
                    While some (e.g. productCode) are not *required*, the only exception they
                    can cause are IndexOutOfBoundsException, in which case the other required
                    indices will not exist making the entry invalid.
                */
                newEntry.put("productCode", oldEntry[0]);
                newEntry.put("stationNumber", oldEntry[1]);
                newEntry.put("year", validateInteger(oldEntry[2]));
                newEntry.put("month", validateInteger(oldEntry[3]));
                newEntry.put("day", validateInteger(oldEntry[4]));
            } catch (NumberFormatException e) {
                majorNumberFormatExceptions++;
                continue;
            } catch (IndexOutOfBoundsException e) {
                majorIndexOutOfBoundsExceptions++;
                continue;
            }

            try {
                // Set rainfallAmount regardless of if it is defined or not (default = 0.0)
                newEntry.put("rainfallAmount", validateDouble(oldEntry[5]));
            } catch (IndexOutOfBoundsException e) {
                undefinedRainfallAmountExceptions++;
                newEntry.put("rainfallAmount", "0.0");
                newData.add(newEntry);
                continue;
            }

            try {
                // Optional extras (not necessary for program function)
                newEntry.put("measurementPeriod", validateInteger(oldEntry[6]));
                newEntry.put("quality", validateBoolean(oldEntry[7]));
                if (oldEntry[6].isEmpty() || oldEntry[7].isBlank()) throw new IllegalArgumentException(); // Occurs if either value are blank
            } catch (IndexOutOfBoundsException e) {
                minorIndexOutOfBoundsExceptions++;
            } catch (IllegalArgumentException e) {
                minorMissingValuesExceptions++;
            } finally {
                newData.add(newEntry);
            }
        }

        System.out.printf("Of a total of %d entries...\n", oldData.size() - 1);
        System.out.printf("- The dataset had %d entries excluded for not being valid\n", majorNumberFormatExceptions + majorIndexOutOfBoundsExceptions);
        System.out.printf("- The dataset had %d entries without a defined rainfall and were defaulted to 0.0\n", undefinedRainfallAmountExceptions);
        System.out.printf("- The dataset had %d entries missing some optional data\n", minorIndexOutOfBoundsExceptions + minorMissingValuesExceptions);

        return newData;
    }

    /**
     * Create a callback function
     */
    private interface Callback<T> {
        T run(String date, ArrayList<HashMap<String, String>> formattedData);
    }

    /**
     * Iterate over each month of the provided data with a callback
     * @param formattedData A String HashMap ArrayList containing formatted rainfall data
     * @param callback A lambda expression to run on each month
     * @return The result of the lambda expression
     */
    private static <T> ArrayList<T> forEachMonth(ArrayList<HashMap<String, String>> formattedData, Callback<T> callback) {
        var monthlyData = new HashMap<String, ArrayList<HashMap<String, String>>>();
        var currentMonthEntries = new ArrayList<HashMap<String, String>>();

        String year = formattedData.get(0).get("year");
        String month = formattedData.get(0).get("month");

        for (var entry : formattedData) {
            if (!entry.get("year").equals(year) || !entry.get("month").equals(month)) {
                // Add month to monthly ArrayList and setup currentMonthEntries for next month
                monthlyData.put(String.format("%s-%s", year, month), currentMonthEntries);
                currentMonthEntries = new ArrayList<HashMap<String, String>>(); // Reset variable for next month
                year = entry.get("year");
                month = entry.get("month");
            }

            // Add entry to month
            currentMonthEntries.add(entry);
        }

        monthlyData.put(String.format("%s-%s", year, month), currentMonthEntries); // Add last month to monthly ArrayList

        // Run lambda expression for each month
        var result = new ArrayList<T>();
        var sortedDates = new ArrayList<String>(monthlyData.keySet());
        Collections.sort(sortedDates, (date1, date2) -> {
            int year1 = Integer.parseInt(date1.split("-")[0]);
            int month1 = Integer.parseInt(date1.split("-")[1]);
            int year2 = Integer.parseInt(date2.split("-")[0]);
            int month2 = Integer.parseInt(date2.split("-")[1]);

            if (year2 > year1) return -1;
            if (year2 < year1) return 1;
            if (month2 > month1) return -1;
            if (month2 < month1) return 1;
            return 0;
        }); // Sort dates so the resulting CSV is in order

        for (String date : sortedDates)
            result.add(callback.run(date, monthlyData.get(date)));

        return result;
    }

    /**
     * Write the analysed data to a new CSV file
     * @param location The filename to give the new CSV
     * @param data The analysed data to be stored
     * @throws IOException Occurs when there is an IO issue with creating or writing to the file
     */
    private static void writeCsvFile(String location, ArrayList<HashMap<String, String>> data) throws IOException {
        new File(location).createNewFile();

        var fw = new FileWriter(location);
        fw.write("Date,Total,Min,Max\n");

        // Write each month to the file
        for (var month : data) {
            fw.write(String.join(",",
                month.get("date"),
                month.get("total"),
                month.get("min"),
                month.get("max")
            ) + "\n");
        }

        fw.close();
    }
}
