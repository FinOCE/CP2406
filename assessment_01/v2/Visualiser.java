import java.io.IOException;
import components.ControlButton;
import components.FileButton;
import components.FileDetails;
import components.FileList;
import components.Graph;
import components.Message;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rainfall.DataHandler;
import rainfall.EntryHandler.NoEntriesException;

public class Visualiser extends Application {
    DataHandler dataset = new DataHandler();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        /*
         * Create some colours to identify different lines. I have allowed for four
         * different colours since that is how many different data files are listed in
         * the subject outline. The colours could be randomised to allow for as many as
         * necessary, however this comes with the possibility of very similar colours
         * making things hard to distinguish apart or just downright invisible.
         */
        Color[] colors = new Color[] { Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN };

        // Create elements of the stage
        var fileList = new FileList(dataset, colors);
        var fileDetails = new FileDetails();
        var message = new Message();
        var graph = new Graph(colors, fileDetails);

        // Create the button row for controls
        var totalBtn = new ControlButton("View Totals", () -> {
            graph.setType("total");
            graph.draw();
        });
        var minimumBtn = new ControlButton("View Minimums", () -> {
            graph.setType("minimum");
            graph.draw();
        });
        var maximumBtn = new ControlButton("View Maximums", () -> {
            graph.setType("maximum");
            graph.draw();
        });
        var fileBtn = new FileButton(stage, file -> {
            try {
                dataset.addFile(file);
            } catch (IOException e) {
                message.say("There was an error trying to read that file");
                return;
            } catch (NoEntriesException e) {
                message.say("The provided file had no valid entries");
                return;
            } catch (IllegalArgumentException e) {
                message.say(e.getMessage());
                return;
            }

            message.say(String.format("Successfully added %s to the dataset!", file.getName()));
            fileList.update(dataset);
            fileDetails.setData(dataset);
            graph.setData(dataset);
            graph.setType("total");
            graph.draw();

            totalBtn.setDisable(false);
            maximumBtn.setDisable(false);
            minimumBtn.setDisable(false);
        });

        var buttonRow = new HBox(fileBtn, totalBtn, minimumBtn, maximumBtn);
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setPadding(new Insets(10));

        var root = new BorderPane(graph);
        BorderPane.setMargin(graph, new Insets(0, 15, 0, 0));
        root.setTop(message);
        BorderPane.setAlignment(message, Pos.CENTER);
        root.setLeft(new VBox(fileList, fileDetails));
        root.setBottom(buttonRow);

        // Create scene
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Rainfall Visualiser - Beta");
        stage.setResizable(false);
        stage.show();
    }
}
