import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RainfallVisualiser extends Application {
    private static final int SIZE = 500;

    private class Graph extends Canvas {
        private static final int PADDING = 50;

        private GraphicsContext ctx = getGraphicsContext2D();

        private ArrayList<HashMap<String, String>> data;
        private String type;
        private double total;
        private double max;
        private double min;

        public Graph(int width, int height) {
            super(width, height);

            ctx.setFill(Color.WHITE);
            ctx.fillRect(0, 0, getWidth(), getHeight());

            ctx.setTextAlign(TextAlignment.CENTER);
            ctx.setFill(Color.BLACK);
            ctx.fillText("Please choose a file below to start.", getWidth()/2, getHeight()/2);
        }

        /**
         * Set the data of the graph
         * @param data Data to plot
         */
        public void setData(ArrayList<HashMap<String, String>> data) {
            this.data = data;

            double total = 0;
            double max = 0;
            double min = 0;

            for (int i = 0; i < data.size(); i++) {
                double totalEntry = Double.parseDouble(data.get(i).get("total"));
                if (totalEntry > total)
                    total = totalEntry;

                double maxEntry = Double.parseDouble(data.get(i).get("max"));
                if (maxEntry > max)
                    max = maxEntry;

                double minEntry = Double.parseDouble(data.get(i).get("min"));
                if (minEntry < min)
                    min = minEntry;
            }

            this.total = total;
            this.max = max;
            this.min = min;
        }

        /**
         * Display that an error occurred
         */
        public void error() {
            ctx.setFill(Color.WHITE);
            ctx.fillRect(0, 0, getWidth(), getHeight());

            ctx.setTextAlign(TextAlignment.CENTER);
            ctx.setFill(Color.BLACK);
            ctx.fillText("The provided file was not valid and could not be plotted.", getWidth()/2, getHeight()/2);
        }

        /**
         * Get the relative Y point from a value
         * @param value A data double value
         * @return A double representing the relative Y position
         */
        private double getRelativeY(double value) {
            double height = getHeight() - 3*PADDING;
            double maxValue = type == "total" ? total : type == "max" ? max : min;
            if (maxValue == 0) maxValue = 1;
            double ratio = value / maxValue;
            return height - height * ratio;
        }

        /**
         * Plot the given data to the graph
         */
        public void plot() throws NumberFormatException {
            drawAxes();

            ctx.setStroke(Color.BLUE);
            double labelWidth = (getWidth() - 3*PADDING) / data.size();

            double prevY = getRelativeY(Double.parseDouble(data.get(0).get(type)));

            for (int i = 0; i < data.size(); i++) {
                double prevX = i == 0 ? 0 : i - 1;

                double y = getRelativeY(Double.parseDouble(data.get(i).get(type)));
                ctx.strokeLine(2*PADDING + prevX*labelWidth, prevY + PADDING, 2*PADDING + i*labelWidth, y + PADDING);
                prevY = y;
            }
        }

        /**
         * Fills the background then draws the axes and labels
         * @throws NumberFormatException
         */
        private void drawAxes() throws NumberFormatException {
            ctx.setFill(Color.WHITE);
            ctx.fillRect(0, 0, getWidth(), getHeight());

            // Write title
            ctx.setTextAlign(TextAlignment.CENTER);
            ctx.setFill(Color.BLACK);
            ctx.setFont(new Font(20));
            String capitalisedType = Character.toUpperCase(type.charAt(0)) + type.substring(1);
            ctx.fillText(String.format("%s rainfall from %s", capitalisedType, file.getName().split("_")[0]), getWidth()/2, PADDING/2);

            // Get domain of the graph and which labels to show
            double labelWidth = (getWidth() - 3*PADDING) / data.size();
            var showLabel = new boolean[data.size()];

            double pxPast = 10;
            for (int i = 0; i < data.size(); i++) {
                pxPast += labelWidth;

                if (pxPast >= 10) {
                    showLabel[i] = true;
                    pxPast = 0;
                }
            }

            // Draw X axis
            ctx.setStroke(Color.BLACK);
            ctx.setTextAlign(TextAlignment.CENTER);
            ctx.setFont(new Font(14));
            ctx.fillText("Date (YYYY-MM)", getWidth()/2, getHeight() - PADDING + 15);
            ctx.setFont(new Font(12));
            ctx.strokeLine(2*PADDING, getHeight() - 2*PADDING, getWidth() - PADDING, getHeight() - 2*PADDING);

            for (int i = 0; i < data.size(); i++) {
                ctx.strokeLine(i * labelWidth + 2*PADDING, getHeight() - 2*PADDING - 1, i * labelWidth + 2*PADDING, getHeight() - 2*PADDING + 1);
                if (showLabel[i]) {
                    ctx.setStroke(Color.LIGHTGREY);
                    ctx.strokeLine(i * labelWidth + 2*PADDING, PADDING, i * labelWidth + 2*PADDING, getHeight() - 2*PADDING - 2);

                    ctx.setStroke(Color.BLACK);
                    ctx.strokeLine(i * labelWidth + 2*PADDING, getHeight() - 2*PADDING - 2, i * labelWidth + 2*PADDING, getHeight() - 2*PADDING + 2);

                    ctx.save();

                    ctx.translate(i * labelWidth + 2*PADDING, getHeight() - 2*PADDING + 3);
                    ctx.rotate(-90);
                    ctx.setTextAlign(TextAlignment.RIGHT);
                    ctx.fillText(data.get(i).get("date"), 0, 4);

                    ctx.restore();
                }
            }

            // Get total of the graph
            int valuesToShow = 15;
            double valueHeight = (getHeight() - 3*PADDING) / valuesToShow;

            // Draw Y axis
            ctx.save();

            ctx.translate(PADDING - 5, getHeight()/2);
            ctx.rotate(-90);
            ctx.setTextAlign(TextAlignment.CENTER);
            ctx.setFont(new Font(14));
            ctx.fillText(String.format("%s monthly rainfall (mm)", capitalisedType), 0, 0);
            ctx.setFont(new Font(12));

            ctx.restore();

            ctx.strokeLine(2*PADDING, PADDING, 2*PADDING, getHeight() - 2*PADDING);

            for (int i = 0; i <= valuesToShow; i++) {
                ctx.setStroke(Color.GREY);
                ctx.strokeLine(2*PADDING + 2, getHeight() - 2*PADDING - i*valueHeight, getWidth() - PADDING, getHeight() - 2*PADDING - i*valueHeight);
                
                ctx.setStroke(Color.BLACK);
                ctx.strokeLine(2*PADDING - 2, getHeight() - 2*PADDING - i*valueHeight, 2*PADDING + 2, getHeight() - 2*PADDING - i*valueHeight);
                ctx.setTextAlign(TextAlignment.RIGHT);
                double maxValue = type == "total" ? total : type == "max" ? max : min;
                if (maxValue == 0) maxValue = 1;
                ctx.fillText(String.format("%.2f", maxValue / valuesToShow * i), 2*PADDING - 3, getHeight() - 2*PADDING - i*valueHeight + 4);
            }
        }

        /**
         * Set the property to plot
         * @param property The property to plot
         */
        public void setType(String type) {
            this.type = type;
        }
    }

    private static interface Callback {
        void run();
    }

    private class ControlButton extends Button {
        public ControlButton(String label, Callback callback) {
            super(label);
            setOnMousePressed(e -> {
                callback.run();
                graph.plot();
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Graph graph;
    private File file;

    public void start(Stage stage) {
        graph = new Graph(SIZE, SIZE);

        var fileBtn = new Button("Choose file...");
        fileBtn.setOnMousePressed(event -> {
            var fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./"));
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
            );
            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                // Fetch data from the CSV
                try {
                    var rainfallData = readCsvFile(file.getPath());
                    var formattedData = formatRainfallData(rainfallData);
                    graph.setData(formattedData);
                    this.file = file;
                    
                    graph.setType("total");
                    graph.plot();
                } catch (Exception e) {
                    graph.error();
                }
            }
        });

        var controls = new HBox(
            fileBtn,
            new ControlButton("Total", () -> graph.setType("total")),
            new ControlButton("Minimum", () -> graph.setType("min")),
            new ControlButton("Maximum", () -> graph.setType("max"))
        );
        controls.setAlignment(Pos.CENTER);

        var root = new BorderPane(graph);
        root.setBottom(controls);

        // Create scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Rainfall Visualiser - Alpha");
        stage.setResizable(false);
        stage.show();
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

    private static String validateDouble(String value) throws NumberFormatException {
        value = value.isEmpty() ? "0" : value;
        return Double.toString(Double.parseDouble(value));
    }

    /**
     * Put all data into hashmaps
     * @param oldData ArrayList of String arrays for old data
     * @return ArrayList of HashMaps storing the same data
     */
    private static ArrayList<HashMap<String, String>> formatRainfallData(ArrayList<String[]> oldData) {
        var newData = new ArrayList<HashMap<String, String>>();

        for (int i = 1; i < oldData.size(); i++) { // Start at 1 to ignore column names
            var oldEntry = oldData.get(i);
            var newEntry = new HashMap<String, String>();

            try {
                newEntry.put("date", oldEntry[0]);
                newEntry.put("total", validateDouble(oldEntry[1]));
                newEntry.put("min", validateDouble(oldEntry[2]));
                newEntry.put("max", validateDouble(oldEntry[3]));

                newData.add(newEntry);
            } catch (Exception e) {
                System.out.printf("The entry one line %d was not valid\n", i + 1);
                continue;
            }
        }

        return newData;
    }
}
