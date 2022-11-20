package product;
import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;

import person.Customer;

public class Order {
    public Order(Customer customer) {
        this.customer = customer;
    }

    public Order(BufferedReader in) throws IOException {
        customer = new Customer(in);

        int size = Integer.parseInt(in.readLine());

        for(int i = 0; i < size; i++) {
            servings.add(new Serving(in));
        }
    }

    public void save(BufferedWriter out) throws IOException {
        customer.save(out);

        out.write(servings.size() + "\n");

        for(Serving serving : servings) {
            serving.save(out);
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addServing(Serving serving) {
        servings.add(serving);
    }

    public Object[] servings() {
        return servings.toArray();
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
        DecimalFormat df = new DecimalFormat( "#.00" );
        double sum = 0;
        for(var t : servings)
             sum += t.price();
        
        s.append("$" + df.format(sum) + " for ");
        s.append(customer.toString() + ":" + "<br/>");
        for(var t : servings)
             s.append(t.toString() + "<br/>");

        return s.toString(); 
    }

    private Customer customer;
    private ArrayList<Serving> servings = new ArrayList<>();
}