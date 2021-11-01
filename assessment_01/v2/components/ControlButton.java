package components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlButton extends Button {
    public ControlButton(String label, Runnable callback) {
        super(label);

        setDisable(true);
        HBox.setMargin(this, new Insets(5));
        setOnMousePressed(e -> callback.run());
    }
}
