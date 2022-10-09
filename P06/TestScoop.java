import java.util.ArrayList;
import java.util.Scanner;

class TestScoop {
    public ArrayList<IceCreamFlavor> flavors = new ArrayList<>();
    public ArrayList<MixInFlavor> mixins = new ArrayList<>();
    public ArrayList<Scoop> scoops = new ArrayList<>();

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

        // /////////////////////
        // Interactive Scoop builder

        TestScoop testScoop = new TestScoop();
        Scanner in = new Scanner(System.in);

        while (true) {

            switch (testScoop.printMenu()) {
                case "m":
                    testScoop.createMixInFlavor(testScoop.mixins);
                    break;
                case "i":
                    testScoop.createIceCreamFlavor(testScoop.flavors);
                    break;
                case "s":
                    testScoop.createScoop(testScoop.flavors, testScoop.mixins, testScoop.scoops);
                    break;
                case "q":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Default case. Try using (m), (i), (s), or (q) next time");
                    System.exit(-1);
                    break;
            }
        }
    }

    public String printMenu() {
        Scanner in = new Scanner(System.in);

        System.out.print("\n================\nMICE Tester v0.1\n================\n");

        if (scoops.isEmpty() != true) {
            System.out.println("List of Ice Cream Scoops:");
            for (Scoop scoop : scoops) {
                System.out.println("\t" + scoop);
            }
            System.out.print("\n");
        }

        if (flavors.isEmpty() || mixins.isEmpty()) {
            System.out.print("*Note: need ice cream flavor and mixin to create scoop\n\nCreate new (m)ixin, (i)ce cream flavor, or (q)uit? ");
        }
        else {
            System.out.print("Create new (m)ixin, (i)ce cream flavor, (s)coop, or (q)uit? ");
        }

        return in.next();
    }

    public void createIceCreamFlavor(ArrayList<IceCreamFlavor> flavors) {
        Scanner in = new Scanner(System.in);

        System.out.println("\nCreating new Ice Cream Flavor!");

        System.out.print("\nName? ");
        String name = in.nextLine();

        System.out.print("\nDescription? ");
        String description = in.nextLine();        
        System.out.print("\nPrice? ");
        int price = in.nextInt();

        System.out.print("\nCost? ");
        int cost = in.nextInt();

        flavors.add(new IceCreamFlavor(name, description, price, cost));
    }

    public void createMixInFlavor(ArrayList<MixInFlavor> mixins) {
        Scanner in = new Scanner(System.in);

        System.out.println("\nCreating new MixIn Flavor!");

        System.out.print("\nName? ");
        String name = in.nextLine();

        System.out.print("\nDescription? ");
        String description = in.nextLine();
        
        System.out.print("\nPrice? ");
        int price = in.nextInt();

        System.out.print("\nCost? ");
        int cost = in.nextInt();

        mixins.add(new MixInFlavor(name, description, price, cost));
    }

    public void createScoop(ArrayList<IceCreamFlavor> flavors, ArrayList<MixInFlavor> mixins, ArrayList<Scoop> scoops) {
        Scanner in = new Scanner(System.in);
        Scanner mix = new Scanner(System.in);

        int i = 0;

        System.out.println("\nCreating a scoop of ice cream!\n");

        for (IceCreamFlavor flavor : flavors) {
            System.out.println(i + ") " + flavor);
            i++;
        }

        System.out.print("\nFlavor? ");
        Scoop scoop = new Scoop(flavors.get(in.nextInt()));

        i = 0;
        for (MixInFlavor mixin : mixins) {
            System.out.println(i + ") " + mixin);
            i++;
        }

        System.out.print("\nMixin? ");
        MixInFlavor mixinFlavor = mixins.get(in.nextInt());

        i = 0;
        for (MixInAmount amount : MixInAmount.values()) {
            System.out.println(i + ") " + amount);
            i++;
        }
        
        System.out.print("\nAmount? ");
        MixInAmount mixInAmount = MixInAmount.values()[in.nextInt()];

        scoop.addMixIn(new MixIn(mixinFlavor, mixInAmount));

        MixIn anotherMixin = anotherMixin();

        while (anotherMixin != null) {
            scoop.addMixIn(anotherMixin);
            anotherMixin = anotherMixin();
        }

        System.out.println("\nAdding " + scoop);
        scoops.add(scoop);
    }

    public MixIn anotherMixin() {
        Scanner in = new Scanner(System.in);
        int i = 0;

        for (i = 0; i < mixins.size(); i++) {
            System.out.println(i + ") " + mixins.get(i));
        }
        
        System.out.print("\n Another mixin? ");
        int input = in.nextInt();

        if (input >= 0) {
            MixInFlavor mixinFlavor = mixins.get(input);
            i = 0;
            for (MixInAmount amount : MixInAmount.values()) {
                System.out.println(i + ") " + amount);
                i++;
            }
        
            System.out.print("\nAmount? ");
            MixInAmount mixInAmount = MixInAmount.values()[in.nextInt()];

            return new MixIn(mixinFlavor, mixInAmount);
        }
        else {
            return null;
        }
    }
}