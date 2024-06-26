package product;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Item {
    public Item(String name, String description, int cost, int price) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.price = price;
    }

    public Item(BufferedReader in) throws IOException {
        name = in.readLine();
        description = in.readLine();
        cost = Integer.parseInt(in.readLine());
        price = Integer.parseInt(in.readLine());
    }

    public void save(BufferedWriter out) throws IOException {
        out.write(name + '\n');        
        out.write(description + '\n');
        out.write("" + cost + '\n');        
        out.write("" + price + '\n');
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public int price() {
        return price;
    }

    public int cost() {
        return cost;
    }

    @Override
    public String toString() {
        return name;
    }

    private String name;
    private String description;
    private int cost;
    private int price;
}