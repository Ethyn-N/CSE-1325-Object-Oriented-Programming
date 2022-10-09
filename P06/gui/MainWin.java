package gui;

import emporium.Emporium;
import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Scoop;
import product.Item;

import java.util.Arrays;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;



public class MainWin extends JFrame {

    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);

        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[] 
            {417, 417, 417};
        layout.rowHeights   = new int[] 
            {60, 60, 60, 60, 60, 60, 60, 60, 60, 60};        
        setLayout(layout);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file                  = new JMenu("File");
        JMenuItem quit                  = new JMenuItem("Quit");

        JMenu     view                  = new JMenu("View");
        JMenuItem viewIceCreamFlavor    = new JMenuItem("Ice Cream Flavor");
        JMenuItem viewMixInFlavor       = new JMenuItem("Mix In Flavor");
        JMenuItem viewScoop             = new JMenuItem("Scoop");

        JMenu     create                = new JMenu("Create");
        JMenuItem createIceCreamFlavor  = new JMenuItem("Ice Cream Flavor");
        JMenuItem createMixInFlavor     = new JMenuItem("Mix In Flavor");
                  createScoop           = new JMenuItem("Scoop");

        JMenu     help                  = new JMenu("Help");
        JMenuItem about                 = new JMenuItem("About");

        quit                    .addActionListener(event -> onQuitClick());

        viewIceCreamFlavor      .addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));
        viewMixInFlavor         .addActionListener(event -> view(Screen.MIX_IN_FLAVORS));
        viewScoop               .addActionListener(event -> view(Screen.SCOOPS));

        createIceCreamFlavor    .addActionListener(event -> onCreateIceCreamFlavorClick());
        createMixInFlavor       .addActionListener(event -> onCreateMixInFlavorClick());
        createScoop             .addActionListener(event -> onCreateScoopClick());

        about                   .addActionListener(event -> onAboutClick());

        file.add(quit);
        
        view.add(viewIceCreamFlavor);
        view.add(viewMixInFlavor);
        view.add(viewScoop);

        create.add(createIceCreamFlavor);
        create.add(createMixInFlavor);
        create.add(createScoop);

        if (emporium.iceCreamFlavors().length == 0)
            createScoop.setEnabled(false);
        else
            createScoop.setEnabled(true);
        

        help.add(about);

        menubar.add(file);
        menubar.add(view);
        menubar.add(create);
        menubar.add(help);

        display = new JLabel("", JLabel.CENTER);
        display.setFont(new Font("Verdana", Font.BOLD, 24));
        display.setForeground(new Color(253, 219, 39));
        getContentPane().setBackground(new Color(0, 177, 210));
        Border border = BorderFactory.createLineBorder(Color.ORANGE);
        display.setBorder(border);

        add(display, constraints(1, 4, 1, 1));

        setJMenuBar(menubar);
        setVisible(true);
    }

    public void onQuitClick() {
        System.exit(0);
    }

    public void onCreateIceCreamFlavorClick() {
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
                IceCreamFlavor flavor = new IceCreamFlavor(names.getText(), descriptions.getText(), Integer.parseInt(costs.getText()), Integer.parseInt(prices.getText()));
                emporium.addIceCreamFlavor(flavor);
                createScoop.setEnabled(true);
                view(Screen.ICE_CREAM_FLAVORS);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Please fill in all inputs with valid data", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void onCreateMixInFlavorClick() {
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
                MixInFlavor flavor = new MixInFlavor(names.getText(), descriptions.getText(), Integer.parseInt(costs.getText()), Integer.parseInt(prices.getText()));
                emporium.addMixInFlavor(flavor);
                view(Screen.MIX_IN_FLAVORS);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Please fill in all inputs with valid data", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void onCreateScoopClick() {
        IceCreamFlavor flavor = (IceCreamFlavor)JOptionPane.showInputDialog(
            this,
            "What flavor would you like?",
            "Create Scoop",
            JOptionPane.QUESTION_MESSAGE,
            null,
            emporium.iceCreamFlavors(),
            null);

        if (flavor == null) {
            view(Screen.SCOOPS);
            return;
        }

        Scoop scoop = new Scoop(flavor);

        if (emporium.mixInFlavors().length == 0) {
            emporium.addScoop(scoop);
            view(Screen.SCOOPS);
            return;
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
            emporium.addScoop(scoop);
            view(Screen.SCOOPS);
            return;
        }
        
        MixInAmount mixInAmount = (MixInAmount)JOptionPane.showInputDialog(
            this,
            "How much of that mixin would you like?",
            "Mixin Amount",
            JOptionPane.QUESTION_MESSAGE,
            null,
            MixInAmount.values() ,
            null);

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
                emporium.addScoop(scoop);
                view(Screen.SCOOPS);
                return;
            }
            
            mixInAmount = (MixInAmount)JOptionPane.showInputDialog(
                this,
                "How much of that mixin would you like?",
                "Mixin Amount",
                JOptionPane.QUESTION_MESSAGE,
                null,
                MixInAmount.values() ,
                null);

            scoop.addMixIn(new MixIn(mixInFlavor, mixInAmount));
        }
    }

    public void onAboutClick() {
        ImageIcon icon = new ImageIcon("/media/sf_Virtual_Machine/cse1325/P06/gui/ice-cream-logo.png");

        JPanel panel = new JPanel();
        panel.setBackground(new Color(102, 205, 170));
        panel.setSize(new Dimension(200, 64));
        panel.setLayout(null);

        JLabel label = new JLabel("Turtles are awesome!!! :D");
        label.setBounds(0, 0, 200, 64);
        label.setFont(new Font("Arial", Font.BOLD, 11));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        UIManager.put("OptionPane.minimumSize",new Dimension(300, 120));        
        JOptionPane.showMessageDialog(this, panel, "Customized Message Dialog", JOptionPane.PLAIN_MESSAGE, icon);

    }

    private void view(Screen screen) {
        if (screen == Screen.ICE_CREAM_FLAVORS)
            display.setText("Ice Cream Flavors: " + Arrays.toString(emporium.iceCreamFlavors()));
        else if (screen == Screen.MIX_IN_FLAVORS)
            display.setText("Mix In Flavors: " + Arrays.toString(emporium.mixInFlavors()));
        else if (screen == Screen.SCOOPS) {
            display.setText("Scoops: " + Arrays.toString(emporium.scoops()));
        }
    }


    public enum Screen {
        ICE_CREAM_FLAVORS,
        MIX_IN_FLAVORS,
        SCOOPS;
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
    private JMenuItem createScoop;

}