import blackjack.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Exercise6_10 extends Application {
    private class CustomButton extends Button {
        public CustomButton(String value) {
            super(value);

            setPrefWidth(board.getWidth()/3.0);
        }
    }

    private Canvas board = new Canvas(515, 415);
    private GraphicsContext ctx = board.getGraphicsContext2D();
    private Image cardImages = new Image("blackjack/cards.png");
    private Deck deck;
    private BlackjackHand hand;
    private BlackjackHand dealerHand;
    private String message;
    private boolean gameInProgress;

    private CustomButton hit;
    private CustomButton stand;
    private CustomButton newGame;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        hit = new CustomButton("Hit me");
        hit.setOnAction(e -> playHit());

        stand = new CustomButton("Stand");
        stand.setOnAction(e -> playStand());

        newGame = new CustomButton("New game");
        newGame.setOnAction(e -> createNewGame());

        HBox buttonBar = new HBox(hit, stand, newGame);

        BorderPane root = new BorderPane();
        root.setCenter(board);
        root.setBottom(buttonBar);

        createNewGame();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Exercise 6.10");
        stage.setResizable(false);
        stage.show();
    }

    private void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
        
        if (gameInProgress) {
            hit.setDisable(false);
            hit.requestFocus();
            stand.setDisable(false);
            newGame.setDisable(true);
        } else {
            hit.setDisable(true);
            stand.setDisable(true);
            newGame.setDisable(false);
            newGame.requestFocus();
        }
    }

    private void playHit() {
        hand.addCard(deck.dealCard());

        if (isBust(hand)) {
            setGameInProgress(false);
            drawBoardWithMessage("It's a bust! You lose.");
        } else if (is21(hand)) {
            setGameInProgress(false);
            drawBoardWithMessage("You got 21, you win!");
        } else if (hand.getCardCount() == 5) {
            setGameInProgress(false);
            drawBoardWithMessage("You drew 5 cards and are still under 21 so you win!");
        } else drawBoardWithMessage("Total hand value: " + hand.getBlackjackValue());
    }

    private void playStand() {
        setGameInProgress(false);

        // Draw cards until <= 16 or have 5 cards
        while(dealerHand.getBlackjackValue() <= 16 && dealerHand.getCardCount() < 5) dealerHand.addCard(deck.dealCard());

        // Determine winner
        if (isBust(dealerHand)) drawBoardWithMessage("Dealer bust, you win!");
        else if (dealerHand.getCardCount() == 5) drawBoardWithMessage("Dealer drew 5 card without busting, you lose!");
        else if (dealerHand.getBlackjackValue() >= hand.getBlackjackValue()) drawBoardWithMessage("Dealer wins, you lose!");
        else drawBoardWithMessage("You win!");
    }

    private void createNewGame() {
        deck = new Deck();
        deck.shuffle();

        dealerHand = new BlackjackHand();
        dealerHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());

        if (isBust(dealerHand)) {
            drawBoardWithMessage("Dealer bust, you win!");
            return;
        } else if (is21(dealerHand)) {
            drawBoardWithMessage("Dealer drew 21, you lose!");
            return;
        }

        hand = new BlackjackHand();
        hand.addCard(deck.dealCard());
        hand.addCard(deck.dealCard());

        if (isBust(hand)) {
            drawBoardWithMessage("You bust, you lose!");
            return;
        } else if (is21(hand)) {
            drawBoardWithMessage("You drew 21, you win!");
            return;
        }

        setGameInProgress(true);
        message = "Total hand value: " + hand.getBlackjackValue();

        drawBoard();
    }

    private boolean isBust(BlackjackHand hand) {
        return hand.getBlackjackValue() > 21;
    }

    private boolean is21(BlackjackHand hand) {
        return hand.getBlackjackValue() == 21;
    }

    private void drawBoard() {
        // Fill background
        ctx.setFill(Color.DARKGREEN);
        ctx.fillRect(0, 0, board.getWidth(), board.getHeight());

        // Write text to canvas
        ctx.setFill(Color.rgb(220,255,220));
        ctx.setFont(Font.font(16));
        ctx.fillText(message, 20, board.getHeight() - 20);
        ctx.fillText("Dealer's Cards:", 20, 27);
        ctx.fillText("Your Cards:", 20, 190);
        
        // Draw cards (only show dealer hand if game is complete)
        if (gameInProgress) for (int i = 0; i < dealerHand.getCardCount(); i++) drawCard(null, 20 + i * 99, 40);
        else for (int i = 0; i < dealerHand.getCardCount(); i++) drawCard(dealerHand.getCard(i), 20 + i * 99, 40);
        for (int i = 0; i < hand.getCardCount(); i++) drawCard(hand.getCard(i), 20 + i * 99, 206);
    }

    private void drawBoardWithMessage(String message) {
        this.message = message;
        drawBoard();
    }

    private void drawCard(Card card, int x, int y) {
        int row = (card == null) ? 4 : 3 - card.getSuit();
        int col = (card == null) ? 2 : card.getValue() - 1;
        double sx = 79 * col;
        double sy = 123 * row;

        ctx.drawImage(cardImages, sx, sy, 79, 123, x, y, 79, 123);
    }
}