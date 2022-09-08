class TestDeck {
    public static void main(String[] args) {
        TestDeck testDeck = new TestDeck();
        testDeck.Test_1();
        testDeck.Test_2();
        testDeck.Test_3();
    }

    public void Test_1() {
        Deck deck = new Deck();
        String actualDeck = "";
        String expectedDeck = "9A9T9U8A8T8U7A7T7U6A6T6U5A5T5U4A4T4U3A3T3U2A2T2U1A1T1U0A0T0U";
        String pop;
        Boolean duplicates = false;
        String duplicatedCards = "";

        while (deck.isEmpty() != true)
        {
            if (actualDeck.contains(pop = deck.deal().toString())) {
                duplicates = true;
                duplicatedCards += " " + pop;
            }
            actualDeck += pop;
        }

        if (actualDeck.equals(expectedDeck) != true) {
            if (duplicates) {
                System.err.println(System.lineSeparator() + "ERROR: Constructor duplicated cards");
                System.err.println("    Expected: " + expectedDeck);
                System.err.println("    Actual:   " + actualDeck);
                System.err.println("    Duplicated cards:" + duplicatedCards + System.lineSeparator());
            }
            if (actualDeck.length() < expectedDeck.length()) {
                System.err.println(System.lineSeparator() + "ERROR: Constructor failed to create cards");
                System.err.println("    Expected: " + expectedDeck);
                System.err.println("    Actual:   " + actualDeck + System.lineSeparator());
            }
        }        
    }

    public void Test_2() {
        Deck deck_1 = new Deck();
        Deck deck_2 = new Deck();

        String cards_1 = "";
        String cards_2 = "";

        deck_1.shuffle();
        deck_2.shuffle();

        while (deck_1.isEmpty() != true && deck_2.isEmpty() != true)
        {
            cards_1 += deck_1.deal().toString();
            cards_2 += deck_2.deal().toString();
        }

        if (cards_1.equals(cards_2)) System.err.println(System.lineSeparator() + "ERROR: Shuffle works poorly - 30 matches" + System.lineSeparator());
    }

    public void Test_3() {
        Deck deck = new Deck();

        for (int i = 0; i < 29; i++) {
            if (deck.isEmpty() != true)
                deck.deal();
            else
                System.exit(-1);
        }

        deck.deal();
        if (deck.isEmpty() != true) System.exit(-1);

        try {
            deck.deal();
        } catch (Exception e) {
            System.err.println(System.lineSeparator() + "ERROR: " + e.getMessage() + System.lineSeparator());
            System.exit(-1);
        }
    }
}