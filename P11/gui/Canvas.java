package gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Canvas extends JPanel {
    public Canvas() {}

    double angle = 115;
    static double RADIUS = 85;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d_star = (Graphics2D) g.create();
        Graphics2D g2d_image = (Graphics2D) g.create();
        Graphics2D g2d_string = (Graphics2D) g.create();

        // visually smooth the lines
        g2d_star.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d_star.setStroke(new BasicStroke(2.0f));
        //g2d.setPaint(Color.RED);
        // set the origin to the center of the panel
        g2d_star.translate(500,300);
        g2d_star.rotate(angle);
        // draw the star in the center of the panel
        drawStar(g2d_star);

        try {
            BufferedImage image = ImageIO.read(new File("gui/ice-cream-logo.png"));
            g2d_image.drawImage(image, 330, 200, this);
        } catch(IOException e) {
            e.printStackTrace();
        }

        g2d_string.setFont(new Font("Verdana", Font.BOLD, 28));
        g2d_string.drawString("Ethyn Nguyen", 25, 25);
        
        
        
    }

    public static void drawStar(Graphics2D g) {        
        int a = (int)(RADIUS*Math.cos(Math.toRadians(54)));
        int b = (int)(RADIUS*Math.sin(Math.toRadians(54)));
        int y = (int)(a*Math.tan(Math.toRadians(72)))+b;
        for (int i = 0; i < 5; i++) {
            if (i < 3)
                g.setColor(new Color(255, 128, 162));
            else
                g.setColor(new Color(255, 218, 217));
            
            g.drawLine(-a,b,0,y);
            g.drawLine(a,b,0,y);
            g.rotate(Math.toRadians(72));
        }
    }
}