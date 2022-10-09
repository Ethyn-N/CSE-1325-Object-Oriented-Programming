package emporium;
import java.util.ArrayList;

class Emporium {
    public void addMixInFlavor(MixInFlavor flavor) {
        mixInFlavors.add(flavor);
    }

    public void addIceCreamFlavor(IceCreamFlavor flavor) {
        iceCreamFlavors.add(flavor);
    }

    public void addScoop(Scoop scoop) {
        scoops.add(scoop);
    }

    public Object[] mixInFlavors() {
        return mixInFlavors.toArray();
    }

    public Object[] iceCreamFlavors() {
        return iceCreamFlavors.toArray();
    }

    public Object[] scoops() {
        return scoops.toArray();
    }

    private ArrayList<MixInFlavor> mixInFlavors;
    private ArrayList<IceCreamFlavor> iceCreamFlavors;
    private ArrayList<Scoop> scoops;
}