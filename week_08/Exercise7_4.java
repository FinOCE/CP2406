import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class Exercise7_4 extends Application {
    /**
     * Represent a shape on the canvas
     */
    public class Shape {
        public double x;
        public double y;
        public double w;
        public double h;
        public Color color;

        public Shape(double x, double y, double w, double h, Color color) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.color = color;
        }
    }

    public static final int width = 400;
    public static final int height = 400;

    private GraphicsContext ctx;

    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private int currentlyDragged = -1;

    // diffX and diffY are used to grab the shape and drag without it teleporting so the top left corner is at the cursor
    private double diffX;
    private double diffY;

    public void start(Stage stage) {
        Canvas canvas = new Canvas(width, height);

        ctx = canvas.getGraphicsContext2D();
        ctx.setFill(Color.WHITE);
        ctx.fillRect(0, 0, width, height);

        shapes.add(new Shape(25, 25, 25, 25, Color.RED));
        shapes.add(new Shape(width - 50, height - 50, 25, 25, Color.BLUE));

        render();
        
        BorderPane root = new BorderPane(canvas);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Exercise 7.4");
        stage.setResizable(false);
        stage.show();

        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));
    }

    /**
     * Draw shapes onto canvas
     */
    public void render() {
        ctx.setStroke(Color.BLACK);

        for (Shape shape : shapes) {
            ctx.setFill(shape.color);
            ctx.fillRect(shape.x, shape.y, shape.w, shape.h);
            ctx.strokeRect(shape.x, shape.y, shape.w, shape.h);
        }
    }

    /**
     * Handle when a mouse button is pressed
     */
    public void mousePressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        if (e.getButton() == MouseButton.SECONDARY || (e.isShiftDown() && e.getButton() == MouseButton.PRIMARY)) {
            // Right click or shift+left click
            for (int i = shapes.size() - 1; i >= 0; i--) {
                var shape = shapes.get(i);

                if (shape.x < x && x < shape.x + shape.w && shape.y < y && y < shape.y + shape.h) {
                    diffX = x - shape.x;
                    diffY = y - shape.y;

                    currentlyDragged = i;
                    break; // No need to continue loop since dragged shape is found
                }
            }
        } else {
            // Other mouse presses
            Color color = Color.color(Math.random(), Math.random(), Math.random(), Math.random()*0.5 + 0.5);
            shapes.add(new Shape(x - 12.5, y - 12.5, 25, 25, color));

            render();
        }
    }

    /**
     * Handle when a mouse button is released
     */
    public void mouseReleased(MouseEvent e) {
        if (currentlyDragged == -1) return;

        var shape = shapes.get(currentlyDragged);
        if (shape.x > width + 50 || shape.x < -50 || shape.y > height + 50 || shape.y < -50)
            shapes.remove(currentlyDragged);

        currentlyDragged = -1; // No shapes are currently dragged
    }

    /**
     * Handle when the mouse drags
     */
    public void mouseDragged(MouseEvent e) {
        if (currentlyDragged == -1) return;

        double x = e.getX();
        double y = e.getY();

        shapes.get(currentlyDragged).x = x - diffX;
        shapes.get(currentlyDragged).y = y - diffY;

        render();
    }

    public static void main(String[] args) {
        launch(args);
    }
}