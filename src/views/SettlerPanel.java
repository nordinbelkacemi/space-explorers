package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import controllers.SelectedSettler;
import models.settler.Settler;

public class SettlerPanel extends UpdatablePanel {
    private List<JLabel> inventory;
    private List<JButton> actionButtons;
    private Settler settler;
    private List<BufferedImage> settlerImages;
    private BufferedImage settlerImage;
    SettlerPanel() {
    	InputStream in;
		try {
			in = new FileInputStream("res/redbig.png");
			settlerImage = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setForeground(Color.white);
		setPreferredSize(new Dimension(300,600));
		setVisible(true);
		update();
    }

    public void update() {
        settler = SelectedSettler.getInstance().getSelectedSettler();
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("SETTLER", 10, 30);
    	g.drawImage(settlerImage,100,50,null);
	}

    public void setSettlerImage(int id) {
        int idx = id - 1;
        settlerImage = settlerImages.get(idx);
    }
}
