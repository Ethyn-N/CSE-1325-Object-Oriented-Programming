package emporium;
import java.util.ArrayList;

import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Item;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Emporium {
    public Emporium(){}

    public Emporium(BufferedReader in) throws IOException {
        int iceCreamSize = Integer.parseInt(in.readLine());

        for(int i = 0; i < iceCreamSize; i++) {
            iceCreamFlavors.add(new IceCreamFlavor(in));
        }

        int mixInSize = Integer.parseInt(in.readLine());

        for(int i = 0; i < mixInSize; i++) {
            mixInFlavors.add(new MixInFlavor(in));
        }

        int scoopSize = Integer.parseInt(in.readLine());

        for(int i = 0; i < scoopSize; i++) {
            scoops.add(new Scoop(in));
        }
    }

    public void save(BufferedWriter out) throws IOException {
        out.write(iceCreamFlavors.size() + "\n");

        for(IceCreamFlavor iceCreamFlavor : iceCreamFlavors) {
            iceCreamFlavor.save(out);
        }

        out.write(mixInFlavors.size() + "\n");

        for(MixInFlavor mixInFlavor : mixInFlavors) {
            mixInFlavor.save(out);
        }

        out.write(scoops.size() + "\n");

        for(Scoop scoop : scoops) {
            scoop.save(out);
        }
    }

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

    private ArrayList<IceCreamFlavor> iceCreamFlavors = new ArrayList<>();
    private ArrayList<MixInFlavor> mixInFlavors = new ArrayList<>();
    private ArrayList<Scoop> scoops = new ArrayList<>();
}