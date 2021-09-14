import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class Exercise6_8 extends Application {
    private class CustomLabel extends Label {
        private String label;

        public CustomLabel(String label) {
            super(label);

            this.label = label;

            setMaxSize(1000, 1000);
        }

        public CustomLabel makeValueLabel() {
            setStyle(
                "-fx-padding: 5px;"
                +"-fx-font: bold 14pt serif;"
                +"-fx-background-color: white;"
            );
            return this;
        }

        public void setValue(String value) {
            setText(label + value);
        }
    }

    private TextArea contentField = new TextArea();
    private Button processBtn = new Button("Process the Text");
    private CustomLabel lineLabel = new CustomLabel("Number of lines: ").makeValueLabel();
    private CustomLabel wordLabel = new CustomLabel("Number of words: ").makeValueLabel();
    private CustomLabel charLabel = new CustomLabel("Number of chars: ").makeValueLabel();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        processBtn.setOnAction(e -> processPressed());

        VBox root = new VBox(5, contentField, new BorderPane(processBtn), lineLabel, wordLabel, charLabel);
        root.setStyle(
            "-fx-background-color: #009;"
            +"-fx-border-color: #009;"
            +"-fx-border-width: 3px;"
        );

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Exercise 6.8");
        stage.setResizable(false);
        stage.show();
    }

    public void processPressed() {
        String text = contentField.getText().trim();

        int chars = text.length();
        int lines = text.split("\n").length;
        int words = text.split("\\s+").length;

        charLabel.setValue(Integer.toString(chars));
        lineLabel.setValue(Integer.toString(lines));
        wordLabel.setValue(Integer.toString(words));
    }
}
