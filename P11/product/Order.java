package product;
import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Order {
    public Order() {}

    public Order(BufferedReader in) throws IOException {
        int size = Integer.parseInt(in.readLine());

        for(int i = 0; i < size; i++) {
            servings.add(new Serving(in));
        }
    }

    public void save(BufferedWriter out) throws IOException {
        out.write(servings.size() + "\n");

        for(Serving serving : servings) {
            serving.save(out);
        }
    }

    public void addServing(Serving serving) {
        servings.add(serving);
    }

    public double price() {
        double sum = 0;

        for (Serving s : servings)
            sum += s.price();

        return sum;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(var t : servings) {
             s.append(t.toString() + "<br/>");
        }
        return s.toString(); 
    }

    private ArrayList<Serving> servings = new ArrayList<>();
}