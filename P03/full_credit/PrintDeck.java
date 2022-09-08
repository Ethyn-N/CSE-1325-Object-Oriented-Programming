class PrintDeck {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        while (deck.isEmpty() != true) {
            System.out.print(" " + deck.deal());
            if (deck.isEmpty())
                System.out.print("\n");
        }
    }
}