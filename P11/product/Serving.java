package product;
import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Serving {
    public Serving(Container container) {
        this.container = container;
    }

    public Serving(BufferedReader in) throws IOException {
        String name = in.readLine();
        String description = in.readLine();
        int maxScoops = Integer.parseInt(in.readLine());
        container = new Container(name, description, maxScoops);

        int scoopSize = Integer.parseInt(in.readLine());

        for(int i = 0; i < scoopSize; i++) {
            scoop.add(new Scoop(in));
        }

        int toppingSize = Integer.parseInt(in.readLine());

        for(int i = 0; i < toppingSize; i++) {
            topping.add(new MixIn(in));
        }
    }

    public void save(BufferedWriter out) throws IOException {
        container.save(out);

        out.write(scoop.size() + "\n");

        for(Scoop s : scoop) {
            s.save(out);
        }

        out.write(topping.size() + "\n");

        for(MixIn mixin : topping) {
            mixin.save(out);
        }
    }

    public void addScoop(Scoop scoop) {
        this.scoop.add(scoop);
    }

    public void addTopping(MixIn topping) {
        this.topping.add(topping);
    }

    public int numScoops() {
        return scoop.size();
    }

    public double price() {
        double sum = 0;

        for (Scoop s : scoop)
            sum += s.price();

        for (MixIn t : topping)
            sum += t.price();
        
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        StringBuilder scoops = new StringBuilder();
        StringBuilder toppings = new StringBuilder();

        int i;

        for (i = 0; i < scoop.size() - 1; i++)
            scoops.append(scoop.get(i).toString() + ", ");

        scoops.append(scoop.get(scoop.size() - 1).toString());

        if (topping.size() != 0) {
            for (i = 0; i < topping.size() - 1; i++)
                toppings.append(topping.get(i).toString() + ", ");

            toppings.append(topping.get(topping.size() - 1).toString());
        }

        
        if(scoop.size() <= 1)
            s.append(container + " with a scoop of " + scoops);
        else
            s.append(container + " with scoops of " + scoops);

        if(!topping.isEmpty())
            s.append(" and topped with " + toppings);

        return s.toString();        
    }

    private Container container;
    private ArrayList<Scoop> scoop = new ArrayList<>();
    private ArrayList<MixIn> topping = new ArrayList<>();
}