package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class SettlerIconPanel extends JPanel {
    private BufferedImage img;
    
    public SettlerIconPanel(BufferedImage img) {
        this.img = img;
        setPreferredSize(new Dimension(35, 35));
        setVisible(false);
    }

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
