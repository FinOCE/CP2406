import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class Exercise6_5 extends Application {
    private final int width = 400;
    private final int height = 400;
    private final int size = 50;
    private final int stroke = 3;

    private GraphicsContext ctx;

    private int prevRow;
    private int prevCol;

    public void renderBoard() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                ctx.setFill(
                    (y + x) % 2 == 0 // Red if even index, black if odd index
                        ? Color.RED
                        : Color.BLACK
                );

                ctx.fillRect(x*size, y*size, size, size);
            }
        }
    }

    public void start(Stage stage) {
        Canvas canvas = new Canvas(width, height);
        ctx = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> mousePressed(e));

        renderBoard();

        BorderPane root = new BorderPane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Exercise 6.5");
        stage.setResizable(false);
        stage.show();
    }
    
    private void mousePressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        int row = (int)x / size;
        int col = (int)y / size;

        renderBoard();
        if (row == prevRow && col == prevCol) return; // If matches, don't draw new stroke

        prevRow = row;
        prevCol = col;

        ctx.setStroke(Color.CYAN);
        ctx.setLineWidth(stroke);
        ctx.strokeRect(row*size+stroke/2, col*size+stroke/2, size-stroke, size-stroke);
    }

    public static void main(String[] args) {
        launch();
    }
}
