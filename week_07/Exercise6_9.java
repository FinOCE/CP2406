import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

public class Exercise6_9 extends Application {
    private static final int width = 512;
    private static final int height = 512;
    private static final int maxPoints = 256;

    GraphicsContext ctx;

    boolean complete = false;
    private double[] coordsX = new double[maxPoints];
    private double[] coordsY = new double[maxPoints];
    private int numberOfPoints = 0;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Canvas canvas = new Canvas(width, height);
        ctx = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> mousePressed(e));
        drawPolygon();

        BorderPane root = new BorderPane(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Exercise 6.8");
        stage.setResizable(false);
        stage.show();
    }

    private boolean pointInRange(double v1, double v2) {
        return Math.abs(v1 - v2) < 3;
    }

    private void mousePressed(MouseEvent e) {
        if (complete) {
            complete = false;
            numberOfPoints = 0;
        }

        double x = e.getX();
        double y = e.getY();

        if (numberOfPoints > 0 && pointInRange(coordsX[0], x) && pointInRange(coordsY[0], y)) {
            complete = true;
            drawPolygon();
            return;
        } else if (e.getButton() == MouseButton.SECONDARY) {
            complete = true;
            drawPolygon();
            return;
        }

        coordsX[numberOfPoints] = x;
        coordsY[numberOfPoints] = y;
        numberOfPoints++;

        if (numberOfPoints == maxPoints) complete = true;

        drawPolygon();
    }

    private void drawPolygon() {
        ctx.setFill(Color.WHITE);
        ctx.fillRect(0, 0, width, height);

        if (!complete) {
            // Not complete polygon
            ctx.setStroke(Color.BLACK);
            ctx.setLineWidth(2);
            for (int i = 0; i < numberOfPoints - 1; i++) {
                ctx.strokeLine(coordsX[i], coordsY[i], coordsX[i+1], coordsY[i+1]);
            }

            ctx.setFill(Color.RED);
            if (numberOfPoints > 0) ctx.fillOval(coordsX[0]-3, coordsY[0]-3, 6, 6);
        } else {
            // Complete polygon
            ctx.setFill(Color.RED);
            ctx.fillPolygon(coordsX, coordsY, numberOfPoints);

            ctx.setStroke(Color.BLACK);
            ctx.setLineWidth(2);
            ctx.strokePolygon(coordsX, coordsY, numberOfPoints);
        }
    }
}
