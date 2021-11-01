package components;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import rainfall.DataHandler;

public class FileDetails extends VBox {
    private DataHandler dataset;

    public void setData(DataHandler dataset) {
        this.dataset = dataset;

        getChildren().clear();

        if (dataset.getFileNames().size() != 0) {
            var intro = new Label("Click on the graph to see details");
            intro.setStyle("-fx-font-weight: bold");
            VBox.setMargin(intro, new Insets(0, 10, 0, 10));
            getChildren().add(intro);
        }
    }

    /**
     * Display the information about a given month
     * 
     * @param year  The year to display
     * @param month The month to display
     */
    public void display(int year, int month) {
        var children = getChildren();
        children.clear();

        var title = new Label(String.format("Details about %d/%d\n\n", month, year));
        title.setStyle("-fx-font-weight: bold");
        VBox.setMargin(title, new Insets(0, 10, 0, 10));
        children.add(title);

        var labels = new ArrayList<Label>();

        for (var fileName : dataset.getFileNames()) {
            var entryHandler = dataset.getFileData(fileName);
            var entries = entryHandler.getEntries(year, month);

            if (entries.size() == 0)
                continue;

            double total = entryHandler.getTotal(entries);
            double maximum = entryHandler.getMaximum(entries);
            double minimum = entryHandler.getMinimum(entries);

            String text = new String();
            text += String.format("%s \n", fileName);
            text += String.format("  Total: %.2f \n", total);
            text += String.format("  Maximum: %.2f \n", maximum);
            text += String.format("  Minimum: %.2f \n\n", minimum);

            var label = new Label(text);
            VBox.setMargin(label, new Insets(0, 10, 5, 10));

            labels.add(label);
        }

        children.addAll(labels);
    }
}
