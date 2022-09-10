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
            Rank rank = new Rank(i);
            for (Suit suit: Suit.values()) { 
                Card card = new Card(rank, suit);
                deck.push(card);
            }
        }
    }

    public int getRank(Deck deck) {
        String string = deck.deal().toString();
        int rank = Integer.parseInt(string.replaceAll("[\\D]", ""));

        return rank;
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

    public void take(Card card1, Card card2) {
        deck.push(card1);
        deck.push(card2);
    }

    public int size() {
        return deck.size();
    }

    public boolean isEmpty() {
        if (deck.empty()) 
            return true;
        else
            return false;
    }

    private Stack<Card> deck = new Stack<Card>();
}