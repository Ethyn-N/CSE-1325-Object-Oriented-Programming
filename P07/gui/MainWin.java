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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import javax.swing.border.Border;

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
        setSize(900, 600);

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

        createScoopButton = new JButton(new ImageIcon(new ImageIcon("gui/create-scoop-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          createScoopButton.setActionCommand("Create scoop");
          createScoopButton.setToolTipText("Create a scoop of ice cream");
          createScoopButton.setBorder(blackBorder);
          toolbar.add(createScoopButton);
          createScoopButton.addActionListener(event -> onCreateScoopClick());

          if (emporium.iceCreamFlavors().length == 0)
            createScoopButton.setEnabled(false);
          else
            createScoopButton.setEnabled(true);

        toolbar.add(Box.createHorizontalStrut(25));

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

        JButton viewScoopsButton = new JButton(new ImageIcon(new ImageIcon("gui/view-scoop-icon.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
          viewScoopsButton.setActionCommand("View scoops");
          viewScoopsButton.setToolTipText("View scoops");
          viewScoopsButton.setBorder(blackBorder);
          toolbar.add(viewScoopsButton);
          viewScoopsButton.addActionListener(event -> view(Screen.SCOOPS));
        
        add(toolbar, BorderLayout.PAGE_START);
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
                createScoopButton.setEnabled(true);
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
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(20);
        borderLayout.setHgap(5);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(102, 205, 170));
        panel.setLayout(borderLayout);

        // Image image = null;
        // URL url = null;
        // try {
        //     url = new URL("https://cdn.pixabay.com/photo/2016/06/02/22/13/ice-1432274_1280.png");
        //     image = ImageIO.read(url);
        // } catch (MalformedURLException ex) {
        //     System.out.println("Malformed URL");
        // } catch (IOException iox) {
        //     iox.printStackTrace();
        // }

        try {
            BufferedImage image = ImageIO.read(new File("gui/ice-cream-logo.png"));
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(150, 360, Image.SCALE_DEFAULT));
            JLabel logo = new JLabel();
            logo.setIcon(imageIcon);
            panel.add(logo, BorderLayout.LINE_START);
        } catch(IOException e) {
            e.printStackTrace();
        }

        // ImageIcon imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(150, 360, Image.SCALE_DEFAULT));
        // JLabel logo = new JLabel();
        // logo.setIcon(imageIcon);
        // panel.add(logo, BorderLayout.LINE_START);

        JLabel title = new JLabel("<html><center>MICE<br>Mavs Ice Cream Emporium</html>");
        title.setFont(new Font("Verdana", Font.BOLD, 24));
        title.setForeground(new Color(205, 101, 136));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        panel.add(title, BorderLayout.PAGE_START);

        JTextPane about = new JTextPane();
        about.setContentType("text/html");
        about.setText("<html><body style=\"font-family: Verdana \"<b><br>Version 0.2<br>Copyright 2022 by Ethyn Nguyen<br>Licensed under GNU GPL 3.0<br>Logo by Schmidsi, per the Pixabay License<br>https://cdn.pixabay.com/photo/2016/06/02/22/13/ice-1432274_1280.png&nbsp&nbsp&nbsp</html>");
        about.setEditable(false);
        about.setBackground(null);
        about.setBorder(null);
        panel.add(about, BorderLayout.CENTER);

        UIManager.put("OptionPane.minimumSize",new Dimension(500, 500));        
        JOptionPane.showMessageDialog(this, panel, "About", JOptionPane.PLAIN_MESSAGE);
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
                
                emporium = new Emporium(br);                   // Open a new game
                view(Screen.SCOOPS);
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
    private File filename = new File("untitled.mice");
    private JMenuItem createScoop;
    private JButton createScoopButton;
    
    private String NAME = "Mice";
    private String VERSION = "1.4J";
    private String FILE_VERSION = "1.0";
    private String MAGIC_COOKIE = "🍦";
}