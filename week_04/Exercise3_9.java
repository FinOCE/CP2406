import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Exercise3_9 extends Application {
    private int getCyclicFrameNumber(int frameNumber, int N) {
        return frameNumber % N;
    }

    private double createCyclicAnimation(int frameNumber, int N, int width) {
        int frame = this.getCyclicFrameNumber(frameNumber, N);
        return (double)width*((double)frame/(double)N);
    }

    private int getOscillatingFrameNumber(int frameNumber, int N) {
        int oscillation = frameNumber % (2*N);
        if (oscillation > N)
            oscillation = (2*N) - oscillation;
        
        return oscillation;
    }

    private double createOscillatingAnimation(int frameNumber, int N, int width) {
        int frame = this.getOscillatingFrameNumber(frameNumber, N);
        return (double)(width-50)*((double)frame/(double)N);
    }

    public void drawFrame(GraphicsContext g, int frameNumber, double elapsedSeconds, int width, int height) {
        // Create blank white background
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Create lines to separate shapes into channels
        g.setFill(Color.BLACK);
        for (int i = 50; i < height; i += 52) {
            g.fillRect(0, i, width, 2);
        }

        // Create cyclic animations
        g.setFill(Color.RED);
        g.fillRect(createCyclicAnimation(frameNumber, 170, width), 0*52, 50, 50);

        g.setFill(Color.LIME);
        g.fillRect(createCyclicAnimation(frameNumber, 60, width), 1*52, 50, 50);

        g.setFill(Color.BLUE);
        g.fillRect(createCyclicAnimation(frameNumber, 100, width), 2*52, 50, 50);

        // Create oscillating animations
        g.setFill(Color.CYAN);
        g.fillRect(createOscillatingAnimation(frameNumber, 80, width), 3*52, 50, 50);

        g.setFill(Color.MAGENTA);
        g.fillRect(createOscillatingAnimation(frameNumber, 150, width), 4*52, 50, 50);

        g.setFill(Color.YELLOW);
        g.fillRect(createOscillatingAnimation(frameNumber, 200, width), 5*52, 50, 50);
    }

    public void start(Stage stage) {
        int width = 600;
        int height = 310;
        Canvas canvas = new Canvas(width,height);
        drawFrame(canvas.getGraphicsContext2D(), 0, 0, width, height);
        
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Exercise 3.9");
        stage.show();
        stage.setResizable(false);

        AnimationTimer anim = new AnimationTimer() {
            private int frameNum;
            private long startTime = -1;
            private long previousTime;

            public void handle(long now) {
                if (startTime < 0) {
                    startTime = previousTime = now;
                    drawFrame(canvas.getGraphicsContext2D(), 0, 0, width, height);
                } else if (now - previousTime > 0.95e9/60) {
                    frameNum++;
                    drawFrame(canvas.getGraphicsContext2D(), frameNum, (now-startTime)/1e9, width, height);
                    previousTime = now;
                }
            }
        };
        anim.start();
    } 

    public static void main(String[] args) {
        launch();
    }
}
