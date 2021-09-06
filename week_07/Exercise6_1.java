import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

public class Exercise6_1 extends Application {
    public static final int width = 400;
    public static final int height = 400;
    
    public static final int shapeSpacing = 1;

    GraphicsContext ctx;

    boolean isDragging = false;
    
    double prevX;
    double prevY;

    public void start(Stage stage) {
        Canvas canvas = new Canvas(width, height);

        ctx = canvas.getGraphicsContext2D();
        ctx.setFill(Color.WHITE);
        ctx.fillRect(0, 0, width, height);
        
        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));
        
        BorderPane root = new BorderPane(canvas);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Exercise 6.1");
        stage.setResizable(false);
        stage.show();
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseButton.SECONDARY) {
            ctx.setFill(Color.WHITE);
            ctx.fillRect(0, 0, width, height);
            isDragging = false;
            return;
        }

        double x = e.getX();
        double y = e.getY();
        
        prevX = x;
        prevY = y;
        isDragging = true;

        addElement(e, x, y);
    }

    public void mouseDragged(MouseEvent e) {
        if (!isDragging) return;
        
        double x = e.getX();
        double y = e.getY();

        if (Math.abs(x - prevX) < shapeSpacing && Math.abs(y - prevY) < shapeSpacing) return;
        
        prevX = x;
        prevY = y;

        addElement(e, x, y);
    }

    private void addElement(MouseEvent e, double x, double y) {
        if (e.isShiftDown()) {
            ctx.setFill(Color.BLUE);
            ctx.fillOval(x-30, y-15, 60, 30);
            ctx.strokeOval(x-30, y-15, 60, 30);
        }
        else {
            ctx.setFill(Color.RED);
            ctx.fillRect(x-30, y-15, 60, 30);
            ctx.strokeRect(x-30, y-15, 60, 30);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}