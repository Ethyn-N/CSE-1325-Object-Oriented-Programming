class Card {
    public Card (Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "" + rank + suit;
    }

    private Rank rank;
    private Suit suit;
}