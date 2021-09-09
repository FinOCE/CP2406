import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.animation.AnimationTimer;

public class Exercise6_4 extends Application {
    /**
     * Represent a die on the canvas
     */
    public class Die {
        public static final int size = 40;

        public int value;
        public int x;
        public int y;

        public Die(int x, int y) {
            roll();
            this.x = x;
            this.y = y;
        }

        public Die(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }

        /**
         * Roll the die
         */
        public void roll() {
            this.value = (int)(1 + Math.random()*6);
        }

        /**
         * Render the die to the canvas
         */
        public void render() {
            ctx.setFill(Color.BLACK);
            ctx.fillRect(x, y, size, size);
            ctx.setFill(Color.WHITE);
            ctx.fillRect(x+2, y+2, size-4, size-4);

            ctx.setFill(Color.BLACK);
            if (value > 1) {
                ctx.fillOval(x+5, y+5, 6, 6); // top left
                ctx.fillOval(x+size-5-6, y+size-5-6, 6, 6); // bottom right
            }
            if (value > 3) {
                ctx.fillOval(x+size-5-6, y+5, 6, 6); // top right
                ctx.fillOval(x+5, y+size-5-6, 6, 6); // bottom left
            }
            if (value == 6) {
                ctx.fillOval(x+5, y+5+12, 6, 6); // middle left
                ctx.fillOval(y+size-5-6, y+5+12, 6, 6); // middle right
            }
            if (value % 2 == 1) ctx.fillOval(x+size-5-6-12, y+size-5-6-12, 6, 6); // middle middle
        }

        private int frame;

        private AnimationTimer timer = new AnimationTimer() {
            public void handle(long time) {
                if (frame % 10 == 0) {
                    roll();
                    render();
                }
                if (frame == 60) {
                    timer.stop();
                    btn.setDisable(false);
                }
                frame++;
            }
        };

        /**
         * Animate the rolling of the die
         */
        public void animate() {
            frame = 0;
            btn.setDisable(true);
            timer.start();
        }
    }

    public static final int width = 100;
    public static final int height = 100;

    private GraphicsContext ctx;
    private Button btn;

    // Values of die preset to 6 and 5 to show all dots are working correctly
    private Die die1 = new Die(6, 5, 5);
    private Die die2 = new Die(5, width-5-Die.size, height-5-Die.size);

    public void start(Stage stage) {
        Canvas canvas = new Canvas(width, height);

        ctx = canvas.getGraphicsContext2D();
        ctx.setFill(Color.rgb(200, 200, 255));
        ctx.fillRect(0, 0, width, height);

        btn = new Button("Roll dice");
        btn.setMaxWidth(1000);
        btn.setOnAction(e -> {
            die1.animate();
            die2.animate();
        });
        
        BorderPane root = new BorderPane(canvas);
        root.setCenter(canvas);
        root.setBottom(btn);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Exercise 6.4");
        stage.setResizable(false);
        stage.show();

        die1.render();
        die2.render();
    }

    public static void main(String[] args) {
        launch(args);
    }
}