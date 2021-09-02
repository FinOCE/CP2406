import textio.TextIO;
import blackjack.*;

public class Exercise5_4 {
    public static void main(String[] args) {
        Deck deck = new Deck(false);

        boolean deal = true;
        do {
            deck.shuffle();

            int cardCount = (int)(Math.random()*5 + 2);
            BlackjackHand hand = new BlackjackHand();

            System.out.println("\n\nYour hand has:");

            for (int i = 0; i < cardCount; i++) {
                Card card = deck.dealCard();
                hand.addCard(card);

                System.out.println("  " + card.toString());
            }

            System.out.println("\nBlackjack value: " + hand.getBlackjackValue());

            System.out.print("\nDeal again? (y/n) ");
            deal = TextIO.getlnBoolean();
        } while (deal);
    }
}
