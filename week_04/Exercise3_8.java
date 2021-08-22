import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Exercise3_8 extends Application {
    public void drawPicture(GraphicsContext g, int width, int height) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                g.setFill(
                    (y + x) % 2 == 0 // Red if even index, black if odd index
                        ? Color.RED
                        : Color.BLACK
                );

                g.fillRect(x*50, y*50, 50, 50);
            }
        }
    }

    public void start(Stage stage) {
        int width = 400;
        int height = 400;
        Canvas canvas = new Canvas(width, height);
        drawPicture(canvas.getGraphicsContext2D(), width, height);

        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Exercise 3.8");
        stage.show();
        stage.setResizable(false);
    } 

    public static void main(String[] args) {
        launch();
    }
}
