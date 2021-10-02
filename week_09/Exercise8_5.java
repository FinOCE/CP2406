import expr.Expr;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Exercise8_5 extends Application {
    public static final boolean CLEAR = false; // Disable to draw multiple expressions on the same graph
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private class Graph extends Canvas {
        private GraphicsContext ctx;

        public Graph(int width, int height) {
            super(width, height);

            ctx = getGraphicsContext2D();

            drawAxes();
        }

        /**
         * Draw the grid, axes, and labels onto the graph
         */
        public void drawAxes() {
            // Fill background
            ctx.setFill(Color.WHITE);
            ctx.fillRect(0, 0, getWidth(), getHeight());

            // Draw grid
            ctx.setStroke(Color.DARKGREY);

            for (double i = getWidth()/11/2; i < getWidth(); i += getWidth()/11) {
                ctx.strokeLine(i, 0, i, getHeight());
                ctx.strokeLine(0, i, getWidth(), i);
            }

            // Draw main axes
            ctx.setStroke(Color.BLACK);
            ctx.strokeLine(0, getHeight()/2, getWidth(), getHeight()/2);
            ctx.strokeLine(getWidth()/2, 0, getWidth()/2, getHeight());

            // Write numbers
            ctx.setFill(Color.rgb(50, 50, 50));

            int pos = -5;
            for (double i = getWidth()/11/2 - 5; i < getWidth(); i += getWidth()/11) {
                String value = Integer.toString(pos++);
                if (pos-1 == 0)
                    continue;
                
                ctx.fillText(value, i, getHeight()/2 + 10);
                ctx.fillText(value, getWidth()/2 - 12, getHeight() - i);
            }

            ctx.fillText("0", getWidth()/2 - 3, getHeight()/2 + 5);
        }

        /**
         * Get the canvas' Y value for a given value
         */
        public double getRelativeY(double value) {
            return getHeight()/2 - (value*(getHeight()/11));
        }

        /**
         * Get the real graph's X value for a given value
         */
        public double getRealX(double value) {
            return (value - getWidth()/2)/(getWidth()/11);
        }

        /**
         * Draw a given expression onto the graph
         */
        public void draw(String expression) throws IllegalArgumentException {
            if (CLEAR)
                drawAxes();

            ctx.setFill(Color.BLACK);

            Expr expr = new Expr(expression);

            double prevX = getRealX(0);
            double prevY = getRelativeY(expr.value(prevX));

            for (double i = 0; i < getWidth(); i++) {
                double x = i;
                double y = getRelativeY(expr.value(getRealX(i)));

                if (Double.isNaN(y))
                    continue;

                ctx.strokeLine(prevX, prevY, x, y);

                prevX = x;
                prevY = y;
            }
        }
    }

    private Label message = new Label("Enter an expression below to draw it.");
    private Graph graph = new Graph(WIDTH, HEIGHT);
    private TextField input = new TextField();
    private Button graphBtn = new Button("Draw Graph");

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        message.setMaxWidth(WIDTH);

        graphBtn.setOnMousePressed(e -> onMousePressed());
        graphBtn.setDefaultButton(true);

        HBox footer = new HBox(new Label("f(x) = "), input, graphBtn);
        HBox.setHgrow(input, Priority.ALWAYS);
        
        BorderPane root = new BorderPane();
        root.setTop(message);
        root.setCenter(graph);
        root.setBottom(footer);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Exercise 8.5");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Handle when the graph button is pressed
     */
    private void onMousePressed() {
        try {
            graph.draw(input.getText());
            message.setText("Your new expression has been graphed!");
        } catch (IllegalArgumentException e) {
            message.setText("The given expression was invalid. Please try again.");
        }

        input.requestFocus();
    }
}