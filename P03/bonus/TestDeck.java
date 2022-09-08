class TestDeck {
    public static void main(String[] args) {
        TestDeck testDeck = new TestDeck();
        testDeck.Test_1();
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
            actualDeck += pop + "";
        }

        if (actualDeck.equals(expectedDeck) != true) {
            if (duplicates) {
                System.err.println("ERROR: Constructor duplicated cards");
                System.err.println("    Expected: " + expectedDeck);
                System.err.println("    Actual:   " + actualDeck);
                System.err.println("    Duplicated cards:" + duplicatedCards);
            }
            if (actualDeck.length() < expectedDeck.length()) {
                System.err.println("ERROR: Constructor failed to create cards");
                System.err.println("    Expected: " + expectedDeck);
                System.err.println("    Actual:   " + actualDeck);
            }
        }        
    }
}