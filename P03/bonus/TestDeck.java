class TestDeck {
    public static void main(String[] args) {
        
    }

    public void Test_1() {
        Deck deck = new Deck();
        String actualDeck = "";
        String pop;

        while (deck.isEmpty() != true)
        {
            if (actualDeck.contains(pop = deck.deal().toString())) {

            }
            else {
                actualDeck += pop + "";
            }
            
        }

        System.out.println(actualDeck);

        
    }
}