package product;
import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Scoop {
    public Scoop(IceCreamFlavor flavor) {
        this.flavor = flavor;
    }

    public Scoop(BufferedReader in) throws IOException {
        String name = in.readLine();
        String description = in.readLine();
        double cost = Double.parseDouble(in.readLine());
        double price = Double.parseDouble(in.readLine());
        flavor = new IceCreamFlavor(name, description, cost, price);

        int size = Integer.parseInt(in.readLine());

        for(int i = 0; i < size; i++) {
            mixins.add(new MixIn(in));
        }
    }

    public void save(BufferedWriter out) throws IOException {
        flavor.save(out);
        out.write(mixins.size() + "\n");

        for(MixIn mixin : mixins) {
            mixin.save(out);
        }

    }

    public void addMixIn(MixIn mixIn) {
        mixins.add(mixIn);
    }

    public double price() {
        double sum = flavor.price();

        for (MixIn mixIn : mixins)
            sum += mixIn.price();

        return sum;
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