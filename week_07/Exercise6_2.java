import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class Exercise6_2 extends Application {
    /**
     * Represent a shape on the canvas
     */
    public class Shape {
        public double x;
        public double y;
        public double w;
        public double h;
        public Color color;
        public boolean isDragged = false;

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

    private Shape shape1 = new Shape(25, 25, 25, 25, Color.RED);
    private Shape shape2 = new Shape(width-50, height-50, 25, 25, Color.BLUE);

    // diffX and diffY are used to grab the shape and drag without it teleporting so the top left corner is at the cursor
    private double diffX;
    private double diffY;

    public void start(Stage stage) {
        Canvas canvas = new Canvas(width, height);

        ctx = canvas.getGraphicsContext2D();
        ctx.setFill(Color.WHITE);
        ctx.fillRect(0, 0, width, height);

        render();
        
        BorderPane root = new BorderPane(canvas);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Exercise 6.2");
        stage.setResizable(false);
        stage.show();

        scene.setOnKeyPressed(e -> keyPressed(e));
        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));
    }

    /**
     * Draw shapes onto canvas
     */
    public void render() {
        ctx.setFill(shape1.color);
        ctx.fillRect(shape1.x, shape1.y, shape1.w, shape1.h);
        ctx.setFill(shape2.color);
        ctx.fillRect(shape2.x, shape2.y, shape2.w, shape2.h);
    }

    /**
     * Handle when a key is pressed
     */
    public void keyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ESCAPE) {
            // When escape is pressed
            shape1.x = 25;
            shape1.y = 25;
            shape2.x = width - 50;
            shape2.y = height - 50;

            render();
        }
    }

    /**
     * Handle when a mouse button is pressed
     */
    public void mousePressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        if (shape1.x < x && x < shape1.x + shape1.w && shape1.y < y && y < shape1.y + shape1.h) {
            // Pressed on shape1
            diffX = x - shape1.x;
            diffY = y - shape1.y;

            shape1.isDragged = true;
        } else if (shape2.x < x && x < shape2.x + shape2.w && shape2.y < y && y < shape2.y + shape2.h) {
            // Pressed on shape2
            diffX = x - shape2.x;
            diffY = y - shape2.y;

            shape2.isDragged = true;
        }
    }

    /**
     * Handle when a mouse button is released
     */
    public void mouseReleased(MouseEvent e) {
        shape1.isDragged = false;
        shape2.isDragged = false;
    }

    /**
     * Handle when the mouse drags
     */
    public void mouseDragged(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        if (shape1.isDragged) {
            // shape1 is being dragged
            shape1.x = x - diffX;
            shape1.y = y - diffY;

            render();
        } else if (shape2.isDragged) {
            // shape2 is being dragged
            shape2.x = x - diffX;
            shape2.y = y - diffY;

            render();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}