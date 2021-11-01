package rainfall;

public class Entry implements Comparable<Entry> {
    public String productCode;
    public String stationNumber;
    public int year;
    public int month;
    public int day;
    public double rainfallAmount;
    public int periodMeasured;
    public boolean quality;

    public Entry(String[] data) throws IllegalArgumentException {
        int i = 0; // Store i in case of error
        try {
            productCode = data[i++];
            stationNumber = data[i++];
            year = Integer.parseInt(data[i++]);
            month = Integer.parseInt(data[i++]);
            day = Integer.parseInt(data[i++]);
            rainfallAmount = Double.parseDouble(data[i++]);
            periodMeasured = Integer.parseInt(data[i++]);
            quality = data[i++] == "Y";
        } catch (Exception e) {
            /*
             * Use default values if no data is provided. If the missing or mis-formatted
             * data is essential, throw an IllegalArgumentException error to ignore the
             * data.
             */
            switch (i) {
                case 6:
                    rainfallAmount = 0;
                case 7:
                    periodMeasured = 0;
                case 8:
                    quality = false;
                    break;
                default:
                    throw new IllegalArgumentException("Essential data was missing or incorrectly formatted");
            }
        }
    }

    @Override
    public int compareTo(Entry other) {
        if (other.year > year)
            return -1;
        if (other.year < year)
            return 1;
        if (other.month > month)
            return -1;
        if (other.month < month)
            return 1;
        return 0;
    }
}
