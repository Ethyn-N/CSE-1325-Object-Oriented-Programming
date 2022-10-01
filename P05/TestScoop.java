import java.util.ArrayList;

class TestScoop {
    public static void main(String[] args) {
        String expected;
        String actual;

        // /////////////////////
        // Scoop initialization

        IceCreamFlavor vanilla = new IceCreamFlavor("Vanilla", "Luscious creamy vanilla bean ice cream", 195, 35);
        Scoop vanillaScoop = new Scoop(vanilla);
        
        expected = "Vanilla";
        actual = vanillaScoop.toString();

        if (expected.compareTo(actual) != 0) {
            System.err.println("FAIL: Scoop initialization failed");
            System.err.println("  Expected: \"" + expected + '"');
            System.err.println("  Actual:   \"" + actual + '"');
        }

        // /////////////////////
        // Scoop with mixins initialization

        IceCreamFlavor chocolate = new IceCreamFlavor("Chocolate", "Luscious creamy chocolate ice cream", 210, 45);
        Scoop chocolateScoop = new Scoop(chocolate);

        MixInFlavor snickersFlavor = new MixInFlavor("Snickers", "Moderately chopped Snickers bars", 99, 35);
        MixIn snickersMixIn = new MixIn(snickersFlavor, MixInAmount.Extra);
        MixInFlavor chocolateChipsFlavor = new MixInFlavor("Chocolate Chips", "Mini chips of semi-sweet chocolate", 79, 24);
        MixIn chocolateChipsMixIn = new MixIn(chocolateChipsFlavor, MixInAmount.Normal);

        chocolateScoop.addMixIn(snickersMixIn);
        chocolateScoop.addMixIn(chocolateChipsMixIn);
        
        expected = "Chocolate with Snickers (Extra), Chocolate Chips";
        actual = chocolateScoop.toString();

        if (expected.compareTo(actual) != 0) {
            System.err.println("FAIL: Scoop with mixins initialization failed");
            System.err.println("  Expected: \"" + expected + '"');
            System.err.println("  Actual:   \"" + actual + '"');
        }
    }
}