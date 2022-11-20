package gui;

import emporium.Emporium;
import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Item;
import product.Container;
import product.Order;
import product.Serving;
import person.Person;
import person.Customer;

import gui.Canvas;

import java.util.Arrays;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javax.swing.border.Border;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.net.MalformedURLException;
import java.net.URL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;



public class MainWin extends JFrame {

    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);

        // GridBagLayout layout = new GridBagLayout();
        // layout.columnWidths = new int[] 
        //     {417, 417, 417};
        // layout.rowHeights   = new int[] 
        //     {60, 60, 60, 60, 60, 60, 60, 60, 60, 60};        
        // setLayout(layout);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file                  = new JMenu("File");
        JMenuItem quit                  = new JMenuItem("Quit");

        JMenu     view                  = new JMenu("View");
        JMenuItem viewCustomer          = new JMenuItem("Customer");
        JMenuItem viewContainer         = new JMenuItem("Container");
        JMenuItem viewIceCreamFlavor    = new JMenuItem("Ice Cream Flavor");
        JMenuItem viewMixInFlavor       = new JMenuItem("Mix In Flavor");
        JMenuItem viewOrder             = new JMenuItem("Order");

        JMenu     create                = new JMenu("Create");
        JMenuItem createCustomer        = new JMenuItem("Customer");
        JMenuItem createContainer       = new JMenuItem("Container");
        JMenuItem createIceCreamFlavor  = new JMenuItem("Ice Cream Flavor");
        JMenuItem createMixInFlavor     = new JMenuItem("Mix In Flavor");
                  createOrder           = new JMenuItem("Order");
        
        JMenu     help                  = new JMenu("Help");
        JMenuItem about                 = new JMenuItem("About");

        quit                    .addActionListener(event -> onQuitClick());

        viewCustomer            .addActionListener(event -> view(Screen.CUSTOMERS));
        viewContainer           .addActionListener(event -> view(Screen.CONTAINERS));
        viewIceCreamFlavor      .addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));
        viewMixInFlavor         .addActionListener(event -> view(Screen.MIX_IN_FLAVORS));
        viewOrder               .addActionListener(event -> view(Screen.ORDERS));
        
        createContainer         .addActionListener(event -> onCreateCustomerClick());
        createContainer         .addActionListener(event -> onCreateContainerClick());
        createIceCreamFlavor    .addActionListener(event -> onCreateIceCreamFlavorClick());
        createMixInFlavor       .addActionListener(event -> onCreateMixInFlavorClick());
        createOrder             .addActionListener(event -> onCreateOrderClick());

        about                   .addActionListener(event -> onAboutClick());

        file.add(quit);
        
        view.add(viewCustomer);
        view.add(viewContainer);
        view.add(viewIceCreamFlavor);
        view.add(viewMixInFlavor);
        view.add(viewOrder);
        
        create.add(createCustomer);
        create.add(createContainer);
        create.add(createIceCreamFlavor);
        create.add(createMixInFlavor);
        create.add(createOrder);
        
        if (emporium.iceCreamFlavors().length == 0 || emporium.containers().length == 0)
            createOrder.setEnabled(false);
        else
            createOrder.setEnabled(true);
        

        help.add(about);

        menubar.add(file);
        menubar.add(view);
        menubar.add(create);
        menubar.add(help);

        display = new JLabel("", JLabel.CENTER);
        display.setPreferredSize(new Dimension(150, 100));
        display.setFont(new Font("Verdana", Font.BOLD, 24));
        display.setForeground(new Color(253, 219, 39));
        getContentPane().setBackground(new Color(0, 177, 210));
        Border border = BorderFactory.createLineBorder(Color.ORANGE);
        display.setBorder(border);

        add(display, BorderLayout.CENTER);

        setJMenuBar(menubar);
        setVisible(true);

        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("Ice Cream Controls");
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        
        JButton save  = new JButton(new ImageIcon(new ImageIcon("gui/save-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          save.setActionCommand("Save");
          save.setToolTipText("Save ice cream flavors, mixins, and scoops");
          save.setBorder(blackBorder);
          toolbar.add(save);
          save.addActionListener(event -> onSaveClick());
        
        JButton saveAs  = new JButton(new ImageIcon(new ImageIcon("gui/save-as-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          saveAs.setActionCommand("Save As");
          saveAs.setToolTipText("Save ice cream flavors, mixins, and scoops as file type");
          saveAs.setBorder(blackBorder);
          toolbar.add(saveAs);
          saveAs.addActionListener(event -> onSaveAsClick());

        JButton open = new JButton(new ImageIcon(new ImageIcon("gui/open-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          open.setActionCommand("Open");
          open.setToolTipText("Open an ice cream file");
          open.setBorder(blackBorder);
          toolbar.add(open);
          open.addActionListener(event -> onOpenClick());

        toolbar.add(Box.createHorizontalStrut(25));

        JButton createCustomerButton = new JButton(new ImageIcon(new ImageIcon("gui/create-customer-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          createCustomerButton.setActionCommand("Create a customer");
          createCustomerButton.setToolTipText("Create a customer");
          createCustomerButton.setBorder(blackBorder);
          toolbar.add(createCustomerButton);
          createCustomerButton.addActionListener(event -> onCreateCustomerClick());

        JButton createContainerButton = new JButton(new ImageIcon(new ImageIcon("gui/create-container-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          createContainerButton.setActionCommand("Create an ice cream container");
          createContainerButton.setToolTipText("Create an ice cream container");
          createContainerButton.setBorder(blackBorder);
          toolbar.add(createContainerButton);
          createContainerButton.addActionListener(event -> onCreateContainerClick());

        JButton createIceCreamFlavorButton = new JButton(new ImageIcon(new ImageIcon("gui/create-flavor-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          createIceCreamFlavorButton.setActionCommand("Create ice cream flavor");
          createIceCreamFlavorButton.setToolTipText("Create an ice cream flavor");
          createIceCreamFlavorButton.setBorder(blackBorder);
          toolbar.add(createIceCreamFlavorButton);
          createIceCreamFlavorButton.addActionListener(event -> onCreateIceCreamFlavorClick());

        JButton createMixInFlavorButton = new JButton(new ImageIcon(new ImageIcon("gui/create-mixin-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          createMixInFlavorButton.setActionCommand("Create mixin flavor");
          createMixInFlavorButton.setToolTipText("Create a mixin flavor");
          createMixInFlavorButton.setBorder(blackBorder);
          toolbar.add(createMixInFlavorButton);
          createMixInFlavorButton.addActionListener(event -> onCreateMixInFlavorClick());

        createOrderButton = new JButton(new ImageIcon(new ImageIcon("gui/create-order-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          createOrderButton.setActionCommand("Create an order");
          createOrderButton.setToolTipText("Create an order of ice cream");
          createOrderButton.setBorder(blackBorder);
          toolbar.add(createOrderButton);
          createOrderButton.addActionListener(event -> onCreateOrderClick());

          if (emporium.iceCreamFlavors().length == 0 || emporium.containers().length == 0)
            createOrderButton.setEnabled(false);
          else
            createOrderButton.setEnabled(true);

        toolbar.add(Box.createHorizontalStrut(25));

        JButton viewCustomerButton = new JButton(new ImageIcon(new ImageIcon("gui/view-customer-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          viewCustomerButton.setActionCommand("View ice cream containers");
          viewCustomerButton.setToolTipText("View ice cream containers");
          viewCustomerButton.setBorder(blackBorder);
          toolbar.add(viewCustomerButton);
          viewCustomerButton.addActionListener(event -> view(Screen.CUSTOMERS));

        JButton viewContainerButton = new JButton(new ImageIcon(new ImageIcon("gui/view-container-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          viewContainerButton.setActionCommand("View ice cream containers");
          viewContainerButton.setToolTipText("View ice cream containers");
          viewContainerButton.setBorder(blackBorder);
          toolbar.add(viewContainerButton);
          viewContainerButton.addActionListener(event -> view(Screen.CONTAINERS));

        JButton viewIceCreamFlavorButton = new JButton(new ImageIcon(new ImageIcon("gui/view-flavor-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          viewIceCreamFlavorButton.setActionCommand("View ice cream flavor");
          viewIceCreamFlavorButton.setToolTipText("View ice cream flavors");
          viewIceCreamFlavorButton.setBorder(blackBorder);
          toolbar.add(viewIceCreamFlavorButton);
          viewIceCreamFlavorButton.addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));

        JButton viewMixInButton = new JButton(new ImageIcon(new ImageIcon("gui/view-mixin-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          viewMixInButton.setActionCommand("View mixin flavors");
          viewMixInButton.setToolTipText("View mixin flavors");
          viewMixInButton.setBorder(blackBorder);
          toolbar.add(viewMixInButton);
          viewMixInButton.addActionListener(event -> view(Screen.MIX_IN_FLAVORS));

        JButton viewOrderButton = new JButton(new ImageIcon(new ImageIcon("gui/view-order-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          viewOrderButton.setActionCommand("View orders");
          viewOrderButton.setToolTipText("View orders");
          viewOrderButton.setBorder(blackBorder);
          toolbar.add(viewOrderButton);
          viewOrderButton.addActionListener(event -> view(Screen.ORDERS));
        
        add(toolbar, BorderLayout.PAGE_START);
    }

    public void onQuitClick() {
        System.exit(0);
    }

    public void onCreateCustomerClick() {
        UIManager.put("OptionPane.minimumSize", new Dimension(50, 50));
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        JTextField names = new JTextField(20);

        JLabel phone = new JLabel("<HTML><br/>Phone</HTML>");
        JTextField phones = new JTextField(20); 

        Object[] objects = {  // Array of widgets to display
            name,   names, 
            phone,   phones};
        
        int option = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "New Customer",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                if (names.getText().isEmpty() || phones.getText().isEmpty())
                    throw new IllegalArgumentException();
                Customer customer = new Customer(names.getText(), phones.getText());
                emporium.addCustomer(customer);
                view(Screen.CUSTOMERS);
            } 
            catch (Exception e) {
                UIManager.put("OptionPane.minimumSize", new Dimension(50, 50));
                JOptionPane.showMessageDialog(this, "Please fill in all inputs with valid data", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void onCreateContainerClick() {
        UIManager.put("OptionPane.minimumSize", new Dimension(250, 300));
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        JTextField names = new JTextField(20);

        JLabel description = new JLabel("<HTML><br/>Description</HTML>");
        JTextField descriptions = new JTextField(20); 
        
        JLabel maxScoop = new JLabel("<HTML><br/>Max Scoops</HTML>");
        JTextField maxScoops = new JTextField(20); 

        Object[] objects = {  // Array of widgets to display
            name,   names, 
            description,   descriptions, 
            maxScoop,    maxScoops};
        
        int option = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "Create an ice cream container",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                if (Integer.parseInt(maxScoops.getText()) <= 0) {
                    UIManager.put("OptionPane.minimumSize", new Dimension(50, 50));
                    throw new IllegalArgumentException("Please use a number that is greater than 0 for max scoops");
                }
                Container container = new Container(names.getText(), descriptions.getText(), Integer.parseInt(maxScoops.getText()));
                emporium.addContainer(container);
                if (emporium.iceCreamFlavors().length > 0) {
                    createOrder.setEnabled(true);
                    createOrderButton.setEnabled(true);
                }
                view(Screen.CONTAINERS);
            } 
            catch (NumberFormatException e) {
                UIManager.put("OptionPane.minimumSize", new Dimension(50, 50));
                JOptionPane.showMessageDialog(this, "Please fill in all inputs with valid data", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            catch (IllegalArgumentException e) {
                UIManager.put("OptionPane.minimumSize", new Dimension(50, 50));
                JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void onCreateIceCreamFlavorClick() {
        UIManager.put("OptionPane.minimumSize", new Dimension(250, 300));
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        JTextField names = new JTextField(20);

        JLabel description = new JLabel("<HTML><br/>Description</HTML>");
        JTextField descriptions = new JTextField(20); 
        
        JLabel cost = new JLabel("<HTML><br/>Cost</HTML>");
        JTextField costs = new JTextField(20); 

        JLabel price = new JLabel("<HTML><br/>Price</HTML>");
        JTextField prices = new JTextField(20);

        Object[] objects = {  // Array of widgets to display
            name,   names, 
            description,   descriptions, 
            cost, costs, 
            price,    prices};
        
        int option = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "Create Ice Cream Flavor",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                if (Double.parseDouble(costs.getText()) < 0 || Double.parseDouble(prices.getText()) < 0)
                    throw new IllegalArgumentException();
                IceCreamFlavor flavor = new IceCreamFlavor(names.getText(), descriptions.getText(), Double.parseDouble(costs.getText()), Double.parseDouble(prices.getText()));
                emporium.addIceCreamFlavor(flavor);
                if (emporium.containers().length > 0) {
                    createOrder.setEnabled(true);
                    createOrderButton.setEnabled(true);
                }
                view(Screen.ICE_CREAM_FLAVORS);
            } catch (Exception e) {
                UIManager.put("OptionPane.minimumSize", new Dimension(50, 50));
                JOptionPane.showMessageDialog(this, "Please fill in all inputs with valid data", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void onCreateMixInFlavorClick() {
        UIManager.put("OptionPane.minimumSize", new Dimension(250, 300));
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        JTextField names = new JTextField(20);

        JLabel description = new JLabel("<HTML><br/>Description</HTML>");
        JTextField descriptions = new JTextField(20); 
        
        JLabel cost = new JLabel("<HTML><br/>Cost</HTML>");
        JTextField costs = new JTextField(20); 

        JLabel price = new JLabel("<HTML><br/>Price</HTML>");
        JTextField prices = new JTextField(20);

        Object[] objects = {  // Array of widgets to display
            name,   names, 
            description,   descriptions, 
            cost, costs, 
            price,    prices};
        
        int option = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "Create Mix In Flavor",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                if (Double.parseDouble(costs.getText()) < 0 || Double.parseDouble(prices.getText()) < 0)
                    throw new IllegalArgumentException();
                MixInFlavor flavor = new MixInFlavor(names.getText(), descriptions.getText(), Double.parseDouble(costs.getText()), Double.parseDouble(prices.getText()));
                emporium.addMixInFlavor(flavor);
                view(Screen.MIX_IN_FLAVORS);
            } catch (Exception e) {
                UIManager.put("OptionPane.minimumSize", new Dimension(50, 50));
                JOptionPane.showMessageDialog(this, "Please fill in all inputs with valid data", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Scoop onCreateScoop() {
        UIManager.put("OptionPane.minimumSize", new Dimension(250, 100));
        IceCreamFlavor flavor = (IceCreamFlavor)JOptionPane.showInputDialog(
            this,
            "Would you like to add a scoop of ice cream?",
            "Create Scoop",
            JOptionPane.QUESTION_MESSAGE,
            null,
            emporium.iceCreamFlavors(),
            null);

        if (flavor == null) {
            return null;
        }

        Scoop scoop = new Scoop(flavor);

        if (emporium.mixInFlavors().length == 0) {
            return scoop;
        }

        MixInFlavor mixInFlavor = (MixInFlavor)JOptionPane.showInputDialog(
        this,
        "Would you like to add a mixin?",
        "Add mixin",
        JOptionPane.QUESTION_MESSAGE,
        null,
        emporium.mixInFlavors(),
        null);
    
        if (mixInFlavor == null) {
            return scoop;
        }
        
        MixInAmount mixInAmount = (MixInAmount)JOptionPane.showInputDialog(
            this,
            "How much of that mixin would you like?",
            "Mixin Amount",
            JOptionPane.QUESTION_MESSAGE,
            null,
            MixInAmount.values() ,
            null);

        if (mixInAmount == null) {
            return scoop;
        }        

        scoop.addMixIn(new MixIn(mixInFlavor, mixInAmount));
        
        while (true) {
            mixInFlavor = (MixInFlavor)JOptionPane.showInputDialog(
            this,
            "Would you like to add another mixin?",
            "Add mixin",
            JOptionPane.QUESTION_MESSAGE,
            null,
            emporium.mixInFlavors(),
            null);
        
            if (mixInFlavor == null) {
                return scoop;
            }
            
            mixInAmount = (MixInAmount)JOptionPane.showInputDialog(
                this,
                "How much of that mixin would you like?",
                "Mixin Amount",
                JOptionPane.QUESTION_MESSAGE,
                null,
                MixInAmount.values() ,
                null);

            if (mixInAmount == null) {
                return scoop;
            }

            scoop.addMixIn(new MixIn(mixInFlavor, mixInAmount));
        }
    }

    public MixIn onCreateTopping() {
        if (emporium.mixInFlavors().length == 0) {
            return null;
        }

        MixInFlavor mixInFlavor = (MixInFlavor)JOptionPane.showInputDialog(
        this,
        "Would you like to add a topping?",
        "Add topping",
        JOptionPane.QUESTION_MESSAGE,
        null,
        emporium.mixInFlavors(),
        null);
    
        if (mixInFlavor == null) {
            return null;
        }
        
        MixInAmount mixInAmount = (MixInAmount)JOptionPane.showInputDialog(
            this,
            "How much of that topping would you like?",
            "Topping Amount",
            JOptionPane.QUESTION_MESSAGE,
            null,
            MixInAmount.values() ,
            null);

        if (mixInAmount == null) {
            return null;
        }

        return new MixIn(mixInFlavor, mixInAmount);
    }

    public Serving onCreateServing(Customer customer) {
        UIManager.put("OptionPane.minimumSize", new Dimension(250, 100));

        JLabel question = new JLabel("<HTML><br/>Would you like to choose a favorite serving?</HTML>");

        Object[] favoriteServings = emporium.favoriteServings(customer);

        JComboBox<Object> fs = new JComboBox<>(favoriteServings);

        Object[] objects = {  // Array of widgets to display
            question, fs};

        if (favoriteServings.length != 0) {
            int option = JOptionPane.showConfirmDialog( // Show the dialog
                this,
                objects,
                "Choose favorite Serving",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION)
                return (Serving)fs.getSelectedItem();
            if (option == JOptionPane.CANCEL_OPTION)
                return null;
        }

        Container container = (Container)JOptionPane.showInputDialog(
            this,
            "What container would you like?",
            "Create Serving",
            JOptionPane.QUESTION_MESSAGE,
            null,
            emporium.containers(),
            null);

        if (container == null) {
            return null;
        }

        Serving serving = new Serving(container);

        int scoopCount = 0;

        if (scoopCount < container.MaxScoops()) {
            Scoop scoop = onCreateScoop();
            if (scoop != null) {
                serving.addScoop(scoop);
                scoopCount++;
            }
            else {
                return null;
            }
        }

        while(scoopCount < container.MaxScoops()) {
            Scoop scoop = onCreateScoop();
            if (scoop != null) {
                serving.addScoop(scoop);
                scoopCount++;
            }
            else {
                break;
            }
        }

        while(true) {
            MixIn topping = onCreateTopping();
            if (topping != null)
                serving.addTopping(topping);
            else
                break;
        }

        return serving;
    }

    public void onCreateOrderClick() {
        UIManager.put("OptionPane.minimumSize", new Dimension(50, 50));
        Order order = new Order();
        boolean isOrder = false;

        Customer customer = (Customer)JOptionPane.showInputDialog(
            this,
            "Who is the customer?",
            "Choose customer",
            JOptionPane.QUESTION_MESSAGE,
            null,
            emporium.customers(),
            null);

        if (customer == null) {
            return;
        }

        order.setCustomer(customer);

        while (true) {
            Serving serving = onCreateServing(customer);
            if (serving != null) {
                order.addServing(serving);
                isOrder = true;
            }
            else {
                break;
            }
        }

        if (isOrder == true) {
            emporium.addOrder(order);
        }

        view(Screen.ORDERS);
    }

    public void onAboutClick() {
        BorderLayout borderLayout = new BorderLayout();
        Border border = BorderFactory.createLineBorder(Color.ORANGE);
        Canvas canvas = new Canvas();
        canvas.setBackground(new Color(102, 205, 170));
        canvas.setLayout(borderLayout);
        canvas.setBorder(border);

        JLabel title = new JLabel("<html><center>MICE<br>Mavs Ice Cream Emporium</html>");
        title.setFont(new Font("Verdana", Font.BOLD, 24));
        title.setForeground(new Color(205, 101, 136));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        canvas.add(title, BorderLayout.PAGE_START);

        JLabel about = new JLabel();
        about.setText("<html><body style=\"font-family: Verdana \"<b><br>" 
                    + "Version 1.0<br>"
                    + "Copyright 2022 by Ethyn Nguyen<br>"
                    + "Licensed under GNU GPL 3.0<br>"
                    + "Logo by Schmidsi, per the Pixabay License<br>"
                    + "https://pixabay.com/vectors/ice-cream-ice-cream-cone-ice-ball-1429596/<br>"
                    + "Ice Cream Cone icon by icons 8 https://img.icons8.com/fluency/48/000000/ice-cream-cone.png<br>"
                    + "Ice cream cone icons created by Superarticons - Flaticon https://www.flaticon.com/free-icons/ice-cream-cone<br>"
                    + "Ice Cream Scoop icon by Icons8 https://icons8.com/icon/36139/ice-cream-scoop<br>"
                    + "Save icon by Icons8 https://icons8.com/icon/42847/save<br>"
                    + "Save as icon by Icons8 https://icons8.com/icon/42946/save-as<br>"
                    + "Import icon by Icons8 https://icons8.com/icon/46613/import<br>"
                    + "Girl and ice cream icon by Icons8 https://icons8.com/icon/82181/girl-and-ice-cream<br>"
                    + "<br><br></html>");
        about.setHorizontalAlignment(JLabel.CENTER);
        about.setBackground(null);
        about.setBorder(null);
        canvas.add(about, BorderLayout.PAGE_END);

        UIManager.put("OptionPane.minimumSize", new Dimension(1000, 700));        
        JOptionPane.showMessageDialog(this, canvas, "About", JOptionPane.PLAIN_MESSAGE);
    }

    public void onOpenClick() {
        final JFileChooser fc = new JFileChooser(filename);  // Create a file chooser dialog
        FileFilter miceFiles = new FileNameExtensionFilter("Mice files", "mice");
        fc.addChoosableFileFilter(miceFiles);         // Add "Mice file" filter
        fc.setFileFilter(miceFiles);                  // Show Mice files only by default
        
        int result = fc.showOpenDialog(this);        // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile();        // Obtain the selected File object
            
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String magicCookie = br.readLine();
                if(!magicCookie.equals(MAGIC_COOKIE)) throw new RuntimeException("Not a mice file");
                String fileVersion = br.readLine();
                if(!fileVersion.equals(FILE_VERSION)) throw new RuntimeException("Incompatible Mice file format");
                
                emporium = new Emporium(br);                   // Open a new emorium

                if (emporium.iceCreamFlavors().length == 0 || emporium.containers().length == 0) {
                    createOrder.setEnabled(false);
                    createOrderButton.setEnabled(false);
                }
                else {
                    createOrder.setEnabled(true);
                    createOrderButton.setEnabled(true);
                }

                view(Screen.ORDERS);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Unable to open " + filename + '\n' + e, 
                    "Failed", JOptionPane.ERROR_MESSAGE); 
             }
        }
    }

    public void onSaveClick() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(MAGIC_COOKIE + '\n');
            bw.write(FILE_VERSION + '\n');
            emporium.save(bw);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open " + filename + '\n' + e,
                "Failed", JOptionPane.ERROR_MESSAGE); 
        }
    }

    public void onSaveAsClick() {
        final JFileChooser fc = new JFileChooser(filename);   // Create a file chooser dialog
        FileFilter miceFiles = new FileNameExtensionFilter("mice files", "mice");
        fc.addChoosableFileFilter(miceFiles);                  // Add "mice file" filter
        fc.setFileFilter(miceFiles);                           // Show only mice files by default
        
        int result = fc.showSaveDialog(this);                 // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) {          // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile();                  // Obtain the selected File object
            if(!filename.getAbsolutePath().endsWith(".mice"))  // Ensure it ends with ".mice"
                filename = new File(filename.getAbsolutePath() + ".mice");
            onSaveClick();                                // Delegate to Save method
        }
    }

    private void view(Screen screen) {
        String title = "";
        StringBuilder s = new StringBuilder();
        switch(screen) {
            case CUSTOMERS:
                title = "Customers";
                for(var t : emporium.customers()) s.append(t.toString() + "<br/>");
                break;
            case CONTAINERS:
                title = "Containers";
                for(var t : emporium.containers()) s.append(t.toString() + "<br/>");
                break;
            case ICE_CREAM_FLAVORS: 
                title = "Ice Cream Flavors";
                for(var t : emporium.iceCreamFlavors()) s.append(t.toString() + "<br/>");
                break;
            case MIX_IN_FLAVORS: 
                title = "Mix In Flavors";
                for(var t : emporium.mixInFlavors()) s.append(t.toString() + "<br/>");
                break;
            case ORDERS: 
                title = "Orders";
                int i = 1;
                for(var t : emporium.orders()) {
                    s.append("Order " + i + " " + t.toString());
                    i++;
                } 
                break;
            default:
                display.setText("PANIC: Invalid Displays type: " + display);
        }
        display.setText("<html><font size=+4>" + title + "<br/></font><font size=+2>" + s.toString() + "</font></html>");
    }

    public enum Screen {
        CUSTOMERS,
        CONTAINERS,
        ICE_CREAM_FLAVORS,
        MIX_IN_FLAVORS,
        ORDERS;
    }

    private GridBagConstraints constraints(int col, int row, int cspan, int rspan) {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridy = row;
        constraint.gridx = col;
        constraint.gridheight = rspan;
        constraint.gridwidth = cspan;
        constraint.anchor = // align to top of cell
        GridBagConstraints.CENTER;
        return constraint;
    }

    private Emporium emporium = new Emporium();
    private JLabel display;
    private File filename = new File("untitled.mice");
    private JMenuItem createOrder;
    private JButton createOrderButton;
    
    private String NAME = "Mice";
    private String VERSION = "0.4";
    private String FILE_VERSION = "0.4";
    private String MAGIC_COOKIE = "🍦";
}