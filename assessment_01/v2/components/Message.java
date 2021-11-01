package components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class Message extends Label {
    public Message() {
        super("Add a file to start using Rainfall Visualiser");

        setPadding(new Insets(10));
    }

    public void say(String label) {
        setText(label);
    }
}
