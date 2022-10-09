import java.util.ArrayList;

class Scoop {
    public Scoop(IceCreamFlavor flavor) {
        this.flavor = flavor;
    }

    public void addMixIn(MixIn mixIn) {
        mixins.add(mixIn);
    }

    @Override
    public String toString() {
        StringBuilder iceCream = new StringBuilder();
        iceCream.append(flavor + " with ");
        if (mixins.isEmpty()) {
            return flavor.toString();
        }
        else {
            for (int i = 0; i < mixins.size(); i++) {
                if (i == 0) {
                    iceCream.append(mixins.get(i));
                }
                else {
                    iceCream.append(", " + mixins.get(i));
                }   
            }
        }
        return iceCream.toString();
    }

    private IceCreamFlavor flavor;
    private ArrayList<MixIn> mixins = new ArrayList<>();
}