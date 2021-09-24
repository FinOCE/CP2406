import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;

public class Exercise7_7 extends Application {
    private class Board extends Canvas {
        private GraphicsContext ctx;

        private boolean gameInProgress = false;
        private int turn = 0;
        private int[][] pieces;

        public Board(int rows, int columns) {
            super(rows*25, columns*25);
            ctx = getGraphicsContext2D();

            pieces = new int[rows][columns];

            drawBoard();
        }

        private void drawBoard() {
            // Draw background
            ctx.setFill(Color.WHITE);
            ctx.fillRect(0, 0, pieces.length*25, pieces[0].length*25);

            // Draw grid lines
            ctx.setLineWidth(2);
            ctx.setStroke(Color.BLACK);
            for (int i = 0; i < getWidth(); i += 25) {
                for (int j = 0; j < getHeight(); j += 25) {
                    ctx.strokeLine(0, j, getWidth(), j);
                }

                ctx.strokeLine(i, 0, i, getHeight());
            }

            // Draw pieces
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[0].length; j++) {
                    // Determine color and fill
                    if (pieces[i][j] == 1) ctx.setFill(Color.WHITE);
                    else if (pieces[i][j] == 2) ctx.setFill(Color.BLACK);
                    else continue;

                    ctx.fillOval(i*25 + 2.5, j*25 + 2.5, 20, 20);

                    // Draw stroke
                    ctx.setLineWidth(1);
                    ctx.setStroke(Color.BLACK);
                    ctx.strokeOval(i*25 + 2.5, j*25 + 2.5, 20, 20);
                }
            }
        }

        private void drawWinningLine(ArrayList<int[]> positions) {
            ctx.setLineWidth(5);
            ctx.setStroke(Color.RED);

            for (int[] p : positions)
                ctx.strokeLine(12.5+p[0]*25, 12.5+p[1]*25, 12.5+p[2]*25, 12.5+p[3]*25);
        }

        private ArrayList<int[]> checkForWinningMove(int row, int col) {
            int[] dirsX = { 1, 0 };
            int[] dirsY = { 0, 1 };

            var positions = new ArrayList<int[]>(); // Using ArrayList to allow for wins in both X and Y directions

            // First do along X, then along Y
            for (int i = 0; i < 2; i++) {
                int chain = 1;
                int[] position = new int[] {row, col, row, col};

                // Count positive
                int nextX = row + dirsX[i];
                int nextY = col + dirsY[i];

                while (nextX >= 0 && nextX < pieces.length && nextY >= 0 && nextY < pieces[0].length && pieces[nextX][nextY] == turn + 1) {
                    chain++;
                    position[2] = nextX;
                    position[3] = nextY;
                    nextX += dirsX[i];
                    nextY += dirsY[i];
                }

                // Count negative
                nextX = row - dirsX[i];
                nextY = col - dirsY[i];

                while (nextX >= 0 && nextX < pieces.length && nextY >= 0 && nextY < pieces[0].length && pieces[nextX][nextY] == turn + 1) {
                    chain++;
                    position[0] = nextX;
                    position[1] = nextY;
                    nextX -= dirsX[i];
                    nextY -= dirsY[i];
                }

                if (chain >= 5) positions.add(position);
            }

            return positions;
        }

        public void mousePressed(MouseEvent e) {
            if (!gameInProgress) return;

            int row = (int)(e.getX()/25);
            int col = (int)(e.getY()/25);

            if (pieces[row][col] != 0) return; // Don't allow a move to be played where a piece already exists

            // Place piece and check for winning move
            pieces[row][col] = turn + 1;

            ArrayList<int[]> positions = checkForWinningMove(row, col);

            if (positions.size() > 0) {
                messageLabel.setText(String.format("%s wins!", turn == 1 ? "Black" : "White"));
                drawBoard();
                drawWinningLine(positions);

                gameInProgress = false;
                newGameButton.setDisable(false);
                resignButton.setDisable(true);
            } else {
                turn = turn == 0 ? 1 : 0;
                messageLabel.setText(String.format("%s's turn", turn == 1 ? "Black" : "White"));
                drawBoard();
            }
        }

        public void mouseMoved(MouseEvent e) {
            if (!gameInProgress) return; // Don't highlight places if game is not in progress

            int row = (int)(e.getX()/25);
            int col = (int)(e.getY()/25);

            drawBoard();

            if (pieces[row][col] != 0) return; // Don't highlight places where a piece already exists

            // Fill highlight shape
            ctx.setFill(turn == 0 ? Color.LIGHTGREY : Color.DARKGRAY);
            ctx.fillOval(row*25 + 2.5, col*25 + 2.5, 20, 20);

            ctx.setLineWidth(1);
            ctx.setStroke(Color.GREY);
            ctx.strokeOval(row*25 + 2.5, col*25 + 2.5, 20, 20);
        }

        public void createNewGame() {
            gameInProgress = true;
            turn = 0;
            pieces = new int[pieces.length][pieces[0].length];

            messageLabel.setText("White's turn");
            newGameButton.setDisable(true);
            resignButton.setDisable(false);
        }

        public void resignGame() {
            gameInProgress = false;

            messageLabel.setText(String.format("%s wins by forfeit!", turn == 0 ? "Black" : "White"));
            newGameButton.setDisable(false);
            resignButton.setDisable(true);
        }
    }

    // Define nodes of the scene
    private Board board = new Board(13, 13);
    private Label messageLabel = new Label("Press \"New Game\" to start");
    private Button resignButton = new Button("Resign");
    private Button newGameButton = new Button("New Game");

    /**
     * Initialise and setup the application
     */
    public void start(Stage stage) {
        // Add event handling to nodes
        board.setOnMousePressed(e -> board.mousePressed(e));
        board.setOnMouseMoved(e -> board.mouseMoved(e));
        newGameButton.setOnAction(e -> board.createNewGame());
        resignButton.setOnAction(e -> board.resignGame());

        // Style nodes
        board.relocate(20, 20);
        newGameButton.relocate(360, 120);
        resignButton.relocate(360, 200);
        messageLabel.relocate(20, 360);

        messageLabel.setTextFill(Color.rgb(100, 255, 100));
        messageLabel.setFont(Font.font(null, FontWeight.BOLD, 18));

        resignButton.setManaged(false);
        resignButton.resize(100, 30);
        resignButton.setDisable(true);
        newGameButton.setManaged(false);
        newGameButton.resize(100, 30);
        
        Pane root = new Pane(board, newGameButton, resignButton, messageLabel);
        root.setStyle(
            "-fx-background-color: darkgreen;"
            +"-fx-border-color: darkred;"
            +"-fx-border-width: 3;"
        );
        root.setPrefWidth(480);
        root.setPrefHeight(400);
        
        // Create scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Exercise 7.7");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Launch the JavaFX application
     */
    public static void main(String[] args) {
        launch(args);
    }
}