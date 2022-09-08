import java.util.Stack;
import java.util.Collections;

class Deck {
    public class DeckEmpty extends IndexOutOfBoundsException { 
        public DeckEmpty(String errorMessage) {
            super(errorMessage);
        }
    }

    public Deck() {
        for (int i = Rank.MIN; i <= Rank.MAX; i++) {
            for (Suit suit: Suit.values()) {
                Rank rank = new Rank(i);
                Card card = new Card(rank, suit);
                deck.push(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card deal() {
        if (isEmpty()) 
            throw new DeckEmpty("Deck is empty!");
        else
            return deck.pop();
    }

    public boolean isEmpty() {
        if (deck.empty()) 
            return true;
        else
            return false;
    }

    private Stack<Card> deck;
}