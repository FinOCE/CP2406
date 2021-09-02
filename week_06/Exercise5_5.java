import textio.TextIO;
import blackjack.*;

public class Exercise5_5 {
    private static BlackjackHand player;
    private static BlackjackHand dealer;
    private static int money = 100;

    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack!");
        System.out.println();

        int gamesPlayed = 0;

        do {
            // Determine bet
            System.out.println("You have $" + money);

            int bet;
            while (true) {
                System.out.print("How much do you want to bet? ");
                bet = TextIO.getlnInt();

                if (bet > money) {
                    System.out.println("You cannot bet more than you have!");
                    System.out.println();
                    continue;
                }

                if (bet <= 0) {
                    System.out.println("Your bet needs to be at least $1!");
                    System.out.println();
                    continue;
                }

                System.out.println();
                break;
            }

            // Display game number
            gamesPlayed++;
            System.out.println("--- Game " + gamesPlayed + " ---");
            System.out.println();

            // Play game, determine winner, add or subtract bet
            boolean userWon = playGame();
            if (userWon) {
                System.out.println("You win!");
                money += bet;
            } else {
                System.out.println("You lost.");
                money -= bet;
            }

            // End if user runs out of money
            if (money == 0) {
                System.out.println();
                System.out.println("You have run out of money. Thanks for playing!");
                break;
            }

            // Offer to play again
            System.out.println();
            System.out.print("Play again? ");
            boolean playAgain = TextIO.getlnBoolean();
            if (!playAgain) break;

            System.out.println();
        } while (true);
    }

    private static boolean playGame() {
        Deck deck = new Deck(false);
        deck.shuffle();

        // Deal dealer's hand
        dealer = new BlackjackHand();
        for (int i = 0; i < 2; i++) dealer.addCard(deck.dealCard());
        displayDealerCards(false);

        if (dealer.getBlackjackValue() == 21) {
            System.out.println("Dealer was dealt 21!");
            return false;
        }

        // Deal player's hand
        player = new BlackjackHand();
        for (int i = 0; i < 2; i++) player.addCard(deck.dealCard());
        displayPlayerCards();

        if (player.getBlackjackValue() == 21) {
            System.out.println("You were dealt 21!");
            return true;
        }
        if (player.getBlackjackValue() > 21) {
            System.out.println("Dealer was dealt a bust!");
            return false;
        }

        // Make player's moves
        do {
            System.out.print("Hit? ");
            boolean hit = TextIO.getlnBoolean();

            if (!hit) break;

            // Deal a card to the player
            Card card = deck.dealCard();
            player.addCard(card);

            System.out.println("Dealt the " + card.toString());

            if (player.getBlackjackValue() > 21) {
                System.out.println("Its a bust!");
                return false;
            }

            displayPlayerCards();
        } while (true);

        // If player hasn't bust, player stands.
        System.out.println("Player stands.");
        System.out.println();

        displayDealerCards(true);

        // Make dealer's moves until until it has a total greater than 16
        while (dealer.getBlackjackValue() <= 16) {
            Card card = deck.dealCard();
            dealer.addCard(card);
            System.out.println("Dealer hits and receives the " + card.toString());
        }

        System.out.println("Dealer's total value: " + dealer.getBlackjackValue());

        if (dealer.getBlackjackValue() > 21) {
            System.out.println("Dealer busts!");
            return true;
        }

        // If neither bust, determine winner
        return player.getBlackjackValue() > dealer.getBlackjackValue();
    }

    private static void displayPlayerCards() {
        System.out.println("You have:");
        for (int i = 0; i < player.getCardCount(); i++) {
            System.out.println("  " + player.getCard(i).toString());
        }

        System.out.println("Total value: " + player.getBlackjackValue());

        System.out.println();
    }

    private static void displayDealerCards(boolean showAll) {
        if (showAll) {
            // If showing all cards
            System.out.println("Dealer has:");
            for (int i = 0; i < dealer.getCardCount(); i++) {
                System.out.println("  " + dealer.getCard(i).toString());
            }

            System.out.println("Total value: " + dealer.getBlackjackValue());

            System.out.println();
        } else {
            // If only showing first card
            BlackjackHand visibleDealerHand = new BlackjackHand();
            visibleDealerHand.addCard(dealer.getCard(0));

            System.out.println("Dealer has " + dealer.getCardCount() + " cards");
            System.out.println("Dealer shows: ");
            System.out.println("  " + visibleDealerHand.getCard(0).toString());
            System.out.println("Total known value: " + visibleDealerHand.getBlackjackValue());

            System.out.println();
        }
    }
}
