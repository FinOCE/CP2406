package rainfall;

public class MonthData {
    public int year;
    public int month;
    public double totalRainfall;
    public double maximumRainfall;
    public double minimumRainfall;

    public MonthData(int year, int month, double total, double max, double min) {
        this.year = year;
        this.month = month;
        totalRainfall = total;
        maximumRainfall = max;
        minimumRainfall = min;
    }
}
