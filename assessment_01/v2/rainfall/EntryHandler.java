package rainfall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class EntryHandler {
    private ArrayList<Entry> entries;
    private ArrayList<String[]> missingEntries;

    public EntryHandler(File file) throws IllegalArgumentException, IOException, NoEntriesException {
        if (!file.isFile())
            throw new IllegalArgumentException("FileHandler requires a file to be provided");

        if (!getFileExtension(file).equals("csv"))
            throw new IllegalArgumentException("Given file must be a CSV");

        // Add all entries from the file to memory
        FileReader fileReader;

        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Given file must exist");
        }

        var bufferedReader = new BufferedReader(fileReader);
        entries = new ArrayList<Entry>();
        missingEntries = new ArrayList<String[]>();

        while (true) {
            String line = bufferedReader.readLine();
            if (line == null)
                break; // Exit the loop once the end of the file is reached

            Entry entry;
            String[] data = line.split(",");

            try {
                entry = new Entry(data);
            } catch (IllegalArgumentException e) {
                missingEntries.add(data);
                continue;
            }

            entries.add(entry);
        }

        bufferedReader.close();

        if (entries.size() == 0)
            throw new NoEntriesException();

        // Sort entries by date
        Collections.sort(entries);
    }

    /**
     * Getter for all valid entries
     * 
     * @return An ArrayList of entries
     */
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    /**
     * Get all valid entries from a specific month
     * 
     * @param year  Year to search
     * @param month Month to search
     * @return An ArrayList of entries from the given month
     */
    public ArrayList<Entry> getEntries(int year, int month) {
        var monthEntries = new ArrayList<Entry>();

        for (var entry : entries)
            if (entry.year == year && entry.month == month)
                monthEntries.add(entry);

        return monthEntries;
    }

    /**
     * Getter for all invalid entries
     * 
     * @return An ArrayList of missing entries
     */
    public ArrayList<String[]> getMissingEntries() {
        return missingEntries;
    }

    /**
     * Save the month data to a new file
     * 
     * @param location Local to save the data to
     * @throws IOException Occurs when the FileWriter fails to write a line
     */
    public void saveToFile(String location) throws IOException {
        new File(location).createNewFile();

        var fileWriter = new FileWriter(location);
        fileWriter.write("Year,Month,Total Rainfall (mm), Maximum Rainfall (mm), Minimum Rainfall (mm)");

        // Add each month to the file
        var monthData = forEachMonth((date, data) -> {
            int year = Integer.parseInt(date.split("-")[0]); // No need to catch because it will ALWAYS be correct
            int month = Integer.parseInt(date.split("-")[1]);

            return new MonthData(year, month, getTotal(data), getMaximum(data), getMinimum(data));
        });

        for (var data : monthData) {
            String year = Integer.toString(data.year);
            String month = Integer.toString(data.month);
            String total = Double.toString(data.totalRainfall);
            String max = Double.toString(data.maximumRainfall);
            String min = Double.toString(data.minimumRainfall);

            String line = String.join(",", year, month, total, max, min) + "\n";
            fileWriter.write(line);
        }

        fileWriter.close();
    }

    /**
     * Get the total rainfall amount from the entire dataset
     * 
     * @return The total rainfall amount from the entire dataset
     */
    public double getTotal() {
        double total = 0;

        for (var entry : entries)
            total += entry.rainfallAmount;

        System.out.println(total);

        return total;
    }

    /**
     * Get the total rainfall amount from the given dataset
     * 
     * @return The total rainfall amount from the given dataset
     */
    public double getTotal(ArrayList<Entry> entries) {
        double total = 0;

        for (var entry : entries)
            total += entry.rainfallAmount;

        return total;
    }

    /**
     * Get the monthly total rainfall amounts
     * 
     * @return The total monthly rainfall amounts
     */
    public ArrayList<Double> getMonthlyTotals() {
        return forEachMonth((date, data) -> {
            double total = 0;

            for (var entry : data)
                total += entry.rainfallAmount;

            return total;
        });
    }

    /**
     * Get the maximum rainfall amount from the entire dataset
     * 
     * @return The maximum rainfall amount from the entire dataset
     */
    public double getMaximum() {
        double maximum = Integer.MIN_VALUE;

        for (var entry : entries)
            if (entry.rainfallAmount > maximum)
                maximum = entry.rainfallAmount;

        return maximum;
    }

    /**
     * Get the maximum rainfall amount from the given dataset
     * 
     * @return The maximum rainfall amount from the given dataset
     */
    public double getMaximum(ArrayList<Entry> entries) {
        double maximum = Integer.MIN_VALUE;

        for (var entry : entries)
            if (entry.rainfallAmount > maximum)
                maximum = entry.rainfallAmount;

        return maximum;
    }

    /**
     * Get the monthly maximum rainfall amounts
     * 
     * @return The maximum monthly rainfall amounts
     */
    public ArrayList<Double> getMonthlyMaximums() {
        return forEachMonth((date, data) -> {
            double maximum = Integer.MIN_VALUE;

            for (var entry : data)
                if (entry.rainfallAmount > maximum)
                    maximum = entry.rainfallAmount;

            return maximum;
        });
    }

    /**
     * Get the minimum rainfall amount from the entire dataset
     * 
     * @return The minimum rainfall amount from the entire dataset
     */
    public double getMinimum() {
        double minimum = Integer.MAX_VALUE;

        for (var entry : entries)
            if (entry.rainfallAmount < minimum)
                minimum = entry.rainfallAmount;

        return minimum;
    }

    /**
     * Get the minimum rainfall amount from the given dataset
     * 
     * @return The minimum rainfall amount from the given dataset
     */
    public double getMinimum(ArrayList<Entry> entries) {
        double minimum = Integer.MAX_VALUE;

        for (var entry : entries)
            if (entry.rainfallAmount < minimum)
                minimum = entry.rainfallAmount;

        return minimum;
    }

    /**
     * Get the monthly minimum rainfall amounts
     * 
     * @return The minimum monthly rainfall amounts
     */
    public ArrayList<Double> getMonthlyMinimums() {
        return forEachMonth((date, data) -> {
            double minimum = Integer.MAX_VALUE;

            for (var entry : data)
                if (entry.rainfallAmount < minimum)
                    minimum = entry.rainfallAmount;

            return minimum;
        });
    }

    public ArrayList<String> getMonthlyDates() {
        return forEachMonth((date, data) -> {
            return date;
        });
    }

    /**
     * Run a callback for each month of entries
     * 
     * @param <T>      The type of the resulting ArrayList
     * @param callback A lambda expression to run on every month
     * @return An ArrayList of results calculated from the callback
     */
    public <T> ArrayList<T> forEachMonth(Callback<T> callback) {
        // Put all entries into ArrayLists by month
        var monthlyEntries = new HashMap<String, ArrayList<Entry>>();
        var currentMonthEntries = new ArrayList<Entry>();

        int year = entries.get(0).year;
        int month = entries.get(0).month;

        for (var entry : entries) {
            if (entry.year != year || entry.month != month) {
                // Add month to monthly HashMap and reset for next month
                monthlyEntries.put(String.format("%s-%s", year, month), currentMonthEntries);
                currentMonthEntries = new ArrayList<Entry>(); // Reset for next month
                year = entry.year;
                month = entry.month;
            }

            // Add entry to month
            currentMonthEntries.add(entry);
        }

        monthlyEntries.put(String.format("%s-%s", year, month), currentMonthEntries);

        // Convert date Set to ArrayList and sort
        var months = new ArrayList<String>();

        for (var date : monthlyEntries.keySet())
            months.add(date);

        Collections.sort(months, (a, b) -> {
            int year1 = Integer.parseInt(a.split("-")[0]);
            int month1 = Integer.parseInt(a.split("-")[1]);
            int year2 = Integer.parseInt(b.split("-")[0]);
            int month2 = Integer.parseInt(b.split("-")[1]);

            if (year2 > year1)
                return -1;
            if (year2 < year1)
                return 1;
            if (month2 > month1)
                return -1;
            if (month2 < month1)
                return 1;
            return 0;
        });

        // Run the given callback for each month
        var result = new ArrayList<T>();

        for (String date : months)
            result.add(callback.run(date, monthlyEntries.get(date)));

        return result;
    }

    /**
     * Get the extension of the given file
     * 
     * @param file The file to find the extension of
     * @return The file extension in lower case (e.g. "csv", "exe", etc.)
     */
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * Custom exception for if there are no entries in memory
     */
    public static class NoEntriesException extends Exception {
        public NoEntriesException() {
            super("The file must have at least one valid entry to be processed");
        }
    }

    /**
     * Callback to run for each month
     */
    public static interface Callback<T> {
        T run(String date, ArrayList<Entry> data);
    }
}
