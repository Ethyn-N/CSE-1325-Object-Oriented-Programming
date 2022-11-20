package product;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class MixIn {
    public MixIn(MixInFlavor flavor, MixInAmount amount) {
        this.flavor = flavor;
        this.amount = amount;
    }

    public MixIn(BufferedReader in) throws IOException {
        amount = MixInAmount.valueOf(in.readLine());

        String name = in.readLine();
        String description = in.readLine();
        double cost = Integer.parseInt(in.readLine());
        double price = Integer.parseInt(in.readLine());
        flavor = new MixInFlavor(name, description, cost, price);
    }

    public void save(BufferedWriter out) throws IOException {
        out.write(amount.toString() + "\n");
        flavor.save(out);
    }

    public double price() {
        switch(amount) {
            case Light:
                return (flavor.price() * 0.8);
            case Normal:
                return flavor.price();
            case Extra:
                return (flavor.price() * 1.2);
            case Drenched:
                return (flavor.price() * 2);
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        if (amount != MixInAmount.Normal)
            return flavor + " (" + amount + ")";
        else
            return flavor.toString();
    }

    private MixInFlavor flavor;
    private MixInAmount amount;
}