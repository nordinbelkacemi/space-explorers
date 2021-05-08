package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class SettlerIconPanel extends JPanel {
    private BufferedImage img;
    
    public SettlerIconPanel(BufferedImage img) {
        this.img = img;
        setPreferredSize(new Dimension(35, 35));
        setBackground(Color.BLACK);
        setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        g.drawImage(img, 0, 0, null);
    }
}
