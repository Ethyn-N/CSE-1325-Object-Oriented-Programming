package product;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Item {
    public Item(String name, String description, double cost, double price) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.price = price;
    }

    public Item(BufferedReader in) throws IOException {
        name = in.readLine();
        description = in.readLine();
        cost = Double.parseDouble(in.readLine());
        price = Double.parseDouble(in.readLine());
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

    public double price() {
        return price;
    }

    public double cost() {
        return cost;
    }

    @Override
    public String toString() {
        return name;
    }

    private String name;
    private String description;
    private double cost;
    private double price;
}