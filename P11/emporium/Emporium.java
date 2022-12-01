package emporium;
import java.util.ArrayList;
import java.util.Properties;

import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Container;
import product.Order;
import product.Item;
import product.Serving;
import person.Person;
import person.Customer;

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

        int containerSize = Integer.parseInt(in.readLine());

        for(int i = 0; i < containerSize; i++) {
            containers.add(new Container(in));
        }

        int orderSize = Integer.parseInt(in.readLine());

        for(int i = 0; i < orderSize; i++) {
            orders.add(new Order(in));
        }

        int customerSize = Integer.parseInt(in.readLine());

        for(int i = 0; i < customerSize; i++) {
            Customer customer = new Customer(in);
            customers.add(customer);
            int favsize = Integer.parseInt(in.readLine());
            for(int j = 0; j < favsize; j++)
                favoriteServings.put(customer, new Serving(in));
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

        out.write(containers.size() + "\n");

        for(Container container : containers) {
            container.save(out);
        }

        out.write(orders.size() + "\n");

        for(Order order : orders) {
            order.save(out);
        }

        out.write(customers.size() + "\n");

        for(Customer customer : customers) {
            customer.save(out);
            out.write("" + favoriteServings.get(customer).length + '\n');
            for(Object serving : favoriteServings.get(customer)) {
                ((Serving) serving).save(out);
            }
        }
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addMixInFlavor(MixInFlavor flavor) {
        mixInFlavors.add(flavor);
    }

    public void addIceCreamFlavor(IceCreamFlavor flavor) {
        iceCreamFlavors.add(flavor);
    }

    public void addContainer(Container container) {
        containers.add(container);
    }

    public void addOrder(Order order) {
        orders.add(order);

        for(var serving : order.servings())
            favoriteServings.put(order.getCustomer(), (Serving)serving);

    }

    public Object[] customers() {
        return customers.toArray();
    }

    public Object[] mixInFlavors() {
        return mixInFlavors.toArray();
    }

    public Object[] iceCreamFlavors() {
        return iceCreamFlavors.toArray();
    }

    public Object[] containers() {
        return containers.toArray();
    }

    public Object[] orders() {
        return orders.toArray();
    }

    public Object[] favoriteServings(Customer customer) {
        return favoriteServings.get(customer);
    }

    private ArrayList<IceCreamFlavor> iceCreamFlavors = new ArrayList<>();
    private ArrayList<MixInFlavor> mixInFlavors = new ArrayList<>();
    private ArrayList<Container> containers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private MultiMap<Customer, Serving> favoriteServings = new MultiMap<>();
}