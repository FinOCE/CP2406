package components;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import rainfall.DataHandler;

public class Graph extends Canvas {
    private static final int PADDING = 50;
    private static final int ROUNDING = 100;

    private GraphicsContext ctx = getGraphicsContext2D();
    private DataHandler dataset;
    private Color[] colors;
    private FileDetails fileDetails;

    private String type = "total";
    private int totalYears;
    private int startYear;
    private double yearWidth;
    private double sizeY;

    public Graph(Color[] colors, FileDetails fileDetails) {
        super(620, 620);

        this.colors = colors;
        this.fileDetails = fileDetails;
        clear();

        setOnMouseMoved(e -> onMouseMoved(e));
        setOnMousePressed(e -> onMousePressed(e));
    }

    /**
     * Set the data to display in the graph
     * 
     * @param dataset The dataset to display
     */
    public void setData(DataHandler dataset) {
        this.dataset = dataset;
    }

    /**
     * Set the type of the graph for what data to show
     * 
     * @param type Either total, minimum, or maximum
     * @throws IllegalArgumentException Occurs when none of the three types were the
     *                                  provided string
     */
    public void setType(String type) throws IllegalArgumentException {
        if (type.equals("total") || type.equals("minimum") || type.equals("maximum"))
            this.type = type;
        else
            throw new IllegalArgumentException("Provided type must be either total, minimum, or maximum");
    }

    /**
     * Clear the canvas
     */
    public void clear() {
        ctx.setFill(Color.WHITE);
        ctx.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
    }

    /**
     * Draw the graph
     */
    public void draw() {
        drawTemplate();
        plot();
    }

    /**
     * Draw the template for the graph excluding the lines themselves
     */
    private void drawTemplate() {
        clear();

        // Draw base lines
        ctx.setStroke(Color.BLACK);
        ctx.strokeLine(PADDING, PADDING, PADDING, getHeight() - PADDING);

        // Draw Axis labels and title
        ctx.setFill(Color.BLACK);
        ctx.setTextAlign(TextAlignment.CENTER);

        ctx.setFont(new Font(16));
        String title = String.format("%s Rainfall", Character.toUpperCase(type.charAt(0)) + type.substring(1));
        ctx.fillText(title, getWidth() / 2, 20);

        ctx.setFont(new Font(14));
        ctx.fillText("Date", getWidth() / 2, getHeight() - 5);

        ctx.save();

        ctx.translate(10, getHeight() / 2);
        ctx.rotate(-90);
        ctx.setTextAlign(TextAlignment.CENTER);
        ctx.fillText(String.format("%s (mm)", title), 0, 4);

        ctx.restore();

        ctx.setFont(new Font(12));

        // Get largest and smallest dates from the dataset
        int maxYear = Integer.MIN_VALUE;
        int maxMonth = Integer.MIN_VALUE;
        int minYear = Integer.MAX_VALUE;
        int minMonth = Integer.MAX_VALUE;

        for (var file : dataset.getFileNames()) {
            for (var entry : dataset.getFileData(file).getEntries()) {
                // Get max date
                if (entry.year > maxYear || (entry.year == maxYear && entry.month > maxMonth)) {
                    maxYear = entry.year;
                    maxMonth = entry.month;
                }

                // Get min date
                if (entry.year < minYear || (entry.year < minYear && entry.month < minMonth)) {
                    minYear = entry.year;
                    minMonth = entry.month;
                }
            }
        }

        totalYears = maxYear - minYear;
        startYear = minYear;

        // Draw X axis
        ctx.strokeLine(PADDING, getHeight() - PADDING, getWidth() - PADDING, getHeight() - PADDING);

        yearWidth = (getWidth() - 2 * PADDING) / (totalYears + 1); // Add 1 to give space for the last year
        double yearWidthPast = yearWidth;
        for (int i = 0; i <= totalYears + 1; i++) {
            yearWidthPast += yearWidth;

            if (yearWidthPast > 10) {
                yearWidthPast = 0;
            } else
                continue;

            double x = i * yearWidth + PADDING;
            double y = getHeight() - PADDING;

            // Draw indicator line (except over the Y axis main line)
            if (i != 0) {
                ctx.setStroke(Color.LIGHTGREY);
                ctx.strokeLine(x, PADDING, x, y);

                ctx.setStroke(Color.BLACK);
                ctx.strokeLine(x, y, x, y - 2);
            }

            // Write label
            ctx.save();

            ctx.translate(x, y + 3);
            ctx.rotate(-90);
            ctx.setTextAlign(TextAlignment.RIGHT);
            ctx.fillText(Integer.toString(minYear + i), 0, 4);

            ctx.restore();
        }

        // Draw Y axis values based on type of graph to show
        switch (type) {
        case "total":
            double total = 0;

            for (var file : dataset.getFileNames()) {
                var totals = dataset.getFileData(file).getMonthlyTotals();

                for (var value : totals) {
                    if (value > total)
                        total = value;
                }
            }

            sizeY = total;
            break;
        case "minimum":
            double minimum = Double.MAX_VALUE;

            for (var file : dataset.getFileNames()) {
                double value = dataset.getFileData(file).getMinimum();
                if (value < minimum)
                    minimum = value;
            }

            if (minimum == 0)
                minimum = 50;

            sizeY = minimum;
            break;
        case "maximum":
            double maximum = 0;

            for (var file : dataset.getFileNames()) {
                double value = dataset.getFileData(file).getMaximum();
                if (value > maximum)
                    maximum = value;
            }

            sizeY = maximum;
            break;
        }

        sizeY = Math.ceil(sizeY / ROUNDING) * ROUNDING; // Round up to the nearest value
        double yHeight = sizeY == 0 ? getHeight() - 2 * PADDING : (getHeight() - 2 * PADDING) / (sizeY / 100);

        ctx.setFill(Color.BLACK);
        ctx.setTextAlign(TextAlignment.RIGHT);

        for (int i = 0; i <= sizeY / ROUNDING; i++) {
            double y = getHeight() - PADDING - i * yHeight;
            ctx.fillText(Integer.toString(i * ROUNDING), PADDING - 2, y + 3);

            ctx.setStroke(Color.LIGHTGREY);
            if (i != 0)
                ctx.strokeLine(PADDING, y, getWidth() - PADDING, y); // Don't draw over main X axis line

            ctx.setStroke(Color.BLACK);
            ctx.strokeLine(PADDING, y, PADDING + 2, y);
        }
    }

    /**
     * Plot the lines onto the graph
     */
    private void plot() {
        int color = 0;

        for (var file : dataset.getFileNames()) {
            // Use provided colour, otherwise use black
            if (color > colors.length)
                ctx.setStroke(Color.BLACK);
            else
                ctx.setStroke(colors[color++]);

            ArrayList<Double> values;
            switch (type) {
            case "total":
                values = dataset.getFileData(file).getMonthlyTotals();
                break;
            case "minimum":
                values = dataset.getFileData(file).getMonthlyMinimums();
                break;
            case "maximum":
                values = dataset.getFileData(file).getMonthlyMaximums();
                break;
            default:
                throw new IllegalArgumentException("Provided type must be either total, minimum, or maximum");
            }

            var dates = dataset.getFileData(file).forEachMonth((date, data) -> date);

            int year = Integer.parseInt(dates.get(0).split("-")[0]);
            int month = Integer.parseInt(dates.get(0).split("-")[1]);
            double prevX = (year + (month / 12.0) - startYear) * yearWidth;

            double prevY = getRelativeY(values.get(0));

            for (int i = 0; i < values.size(); i++) {
                year = Integer.parseInt(dates.get(i).split("-")[0]);
                month = Integer.parseInt(dates.get(i).split("-")[1]);
                double x = (year + (month / 12.0) - startYear) * yearWidth;

                double y = getRelativeY(values.get(i));
                ctx.strokeLine(PADDING + prevX, PADDING + prevY, PADDING + x, PADDING + y);

                prevX = x;
                prevY = y;
            }
        }
    }

    /**
     * Get the relative Y point from a value
     * 
     * @param value A data double value
     * @return A double representing the relative Y position
     */
    private double getRelativeY(double value) {
        double height = getHeight() - 2 * PADDING;
        double ratio = value / sizeY;
        return height - height * ratio;
    }

    /**
     * Handle when mouse moves over the graph
     * 
     * @param e MouseEvent for the movement
     */
    private void onMouseMoved(MouseEvent e) {
        // Draw if data exists
        if (dataset == null)
            return;

        draw();

        // Get year and month that is hovered over
        double x = e.getX();

        if (x < PADDING || x > getWidth() - PADDING)
            return;

        double exact = startYear + (x - PADDING) / yearWidth;
        int year = (int) exact;
        int month = (int) ((exact - (int) exact) * 12) + 1;

        // Draw points on the graph
        int color = 0;

        for (var fileName : dataset.getFileNames()) {
            var entryHandler = dataset.getFileData(fileName);
            var entries = entryHandler.getEntries(year, month);

            double value = 0;

            switch (type) {
            case "total":
                value = entryHandler.getTotal(entries);
                break;
            case "maximum":
                value = entryHandler.getMaximum(entries);
                break;
            case "minimum":
                value = entryHandler.getMinimum(entries);
                break;
            }

            ctx.setStroke(Color.BLACK);
            ctx.strokeLine(x, PADDING, x, getHeight() - PADDING);

            ctx.setFill(colors[color++]);
            ctx.fillOval(x - 4, getRelativeY(value) - 4 + PADDING, 8, 8);
        }
    }

    /**
     * Handle when mouse is pressed on the graph
     * 
     * @param e MouseEvent for the press
     */
    private void onMousePressed(MouseEvent e) {
        // Get year and month that is hovered over
        double x = e.getX();

        if (x < PADDING || x > getWidth() - PADDING)
            return;

        if (dataset == null)
            return;

        double exact = startYear + (x - PADDING) / yearWidth;
        int year = (int) exact;
        int month = (int) ((exact - (int) exact) * 12) + 1;

        fileDetails.display(year, month);
    }
}
