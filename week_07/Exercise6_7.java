import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Exercise6_7 extends Application {
    private class CustomLabel extends Label {
        private String label;

        public CustomLabel(String label) {
            super(label);

            this.label = label;

            setMaxSize(1000, 1000);
        }

        public CustomLabel makeValueLabel() {
            setStyle(
                "-fx-background-color: white;"
                +"-fx-font-family: monospace;"
                +"-fx-font-weight: bold"
            );

            return this;
        }

        public void setLabel(String label) {
            this.label = label;
            setText(label);
        }

        public void setValue(String value) {
            setText(label + value);
        }
    }

    private class CustomButton extends Button {
        public CustomButton(String text) {
            super(text);
            setMaxSize(1000, 1000);
        }
    }

    private StatCalc calc = new StatCalc();

    private CustomLabel message = new CustomLabel("Enter a number, click Enter:");

    private TextField inputField = new TextField();
    private CustomButton enterBtn = new CustomButton("Enter");
    private CustomButton clearBtn = new CustomButton("Clear");

    private CustomLabel entriesLabel = new CustomLabel("Number of entries: ").makeValueLabel();
    private CustomLabel sumLabel = new CustomLabel("Sum: ").makeValueLabel();
    private CustomLabel averageLabel = new CustomLabel("Average: ").makeValueLabel();
    private CustomLabel sdLabel = new CustomLabel("Standard Deviation: ").makeValueLabel();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        message.setFont(Font.font(16));
        message.setTextFill(Color.WHITE);

        enterBtn.setOnAction(e -> enterPressed());
        enterBtn.setDefaultButton(true);
        clearBtn.setOnAction(e -> clearPressed());

        TilePane inputPane = new TilePane(3, 3, inputField, enterBtn, clearBtn);
        inputPane.setPrefColumns(3);

        TilePane rootPane = new TilePane(3, 3, message, inputPane, entriesLabel, sumLabel, averageLabel, sdLabel);
        rootPane.setPrefColumns(1);
        rootPane.setStyle(
            "-fx-border-color: black;"
            +"-fx-border-width: 3;"
            +"-fx-background-color: black;"
        );

        Scene scene = new Scene(rootPane);
        stage.setScene(scene);
        stage.setTitle("Exercise 6.7");
        stage.setResizable(false);
        stage.show();
    }

    public void updateLabels() {
        entriesLabel.setValue(Integer.toString(calc.getCount()));
        sumLabel.setValue(Double.toString(calc.getSum()));
        averageLabel.setValue(Double.toString(calc.getMean()));
        sdLabel.setValue(Double.toString(calc.getStandardDeviation()));

        message.setLabel("Enter a number, click Enter:");
        inputField.selectAll();
        inputField.requestFocus();
    }

    public void enterPressed() {
        try {
            double number = Double.parseDouble(inputField.getText());
            calc.enter(number);
            updateLabels();
        } catch (NumberFormatException e) {
            message.setText("Please enter a valid number.");
            inputField.selectAll();
            inputField.requestFocus();
        }
    }

    public void clearPressed() {
        calc = new StatCalc();
        inputField.setText("");
        updateLabels();
    }
}
