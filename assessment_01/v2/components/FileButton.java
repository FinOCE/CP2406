package components;

import java.io.File;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileButton extends Button {
    public FileButton(Stage stage, Callback callback) {
        super("Add file...");

        HBox.setMargin(this, new Insets(5, 15, 5, 5));
        setOnMousePressed(e -> {
            var fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./data/"));
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV files", "*.csv"),
                new FileChooser.ExtensionFilter("All files", "*.*")
            );
            List<File> files = fileChooser.showOpenMultipleDialog(stage);

            if (files != null)
                for (var file : files)
                    if (file != null)
                        callback.run(file);
        });
    }

    public static interface Callback {
        void run(File file);
    }
}
