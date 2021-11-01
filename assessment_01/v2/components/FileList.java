package components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import rainfall.DataHandler;

public class FileList extends VBox {
    private class ColorDisplay extends Canvas {
        private GraphicsContext ctx = getGraphicsContext2D();

        public ColorDisplay(Color color) {
            super(20, 50);

            ctx.setFill(color);
            ctx.fillOval(getWidth() / 2, getHeight() / 2 - 3, 10, 10);
        }
    }

    private Color[] colors;

    public FileList(DataHandler dataset, Color[] colors) {
        this.colors = colors;
        setMinWidth(225);
        setMaxWidth(225);
        update(dataset);
    }

    /**
     * Update the FileList to contain all new files from the dataset
     * 
     * @param dataset The dataset to pull files from
     */
    public void update(DataHandler dataset) {
        var children = getChildren();
        var fileNames = dataset.getFileNames();

        children.clear();

        int color = 0;
        for (var file : fileNames) {
            var numberOfEntries = dataset.getFileData(file).getEntries().size();

            var colorDisplay = new ColorDisplay(color < colors.length ? colors[color++] : Color.BLACK);

            var label = new Label();
            label.setText(String.format("%s\n%d entries\n", file, numberOfEntries));
            label.setStyle("-fx-padding: 10");

            var hbox = new HBox();
            hbox.getChildren().addAll(colorDisplay, label);
            children.add(hbox);
        }
    }
}
