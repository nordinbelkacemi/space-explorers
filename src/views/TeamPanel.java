package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;


public class TeamPanel extends UpdatablePanel {

    private JButton nextTurnButton;
    private List<BufferedImage> settlerImages = new ArrayList<>();

    public TeamPanel() {
    	try {
    		InputStream in = new FileInputStream("res/redsmall.png");
			settlerImages.add(ImageIO.read(in));
			
			in = new FileInputStream("res/bluesmall.png");
			settlerImages.add(ImageIO.read(in));
			
			in = new FileInputStream("res/greensmall.png");
			settlerImages.add(ImageIO.read(in));
			
			in = new FileInputStream("res/yellowsmall.png");
			settlerImages.add(ImageIO.read(in));
			
			in = new FileInputStream("res/purplesmall.png");
			settlerImages.add(ImageIO.read(in));
			
			in = new FileInputStream("res/orangesmall.png");
			settlerImages.add(ImageIO.read(in));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setForeground(Color.white);
		setPreferredSize(new Dimension(300,150));
		nextTurnButton = new JButton("next turn");
		nextTurnButton.setSize(50, 30);
		nextTurnButton.setBackground(Color.black);
		nextTurnButton.setForeground(Color.white);
		nextTurnButton.setFont(new Font(getFont().getFontName(), Font.BOLD, 20));
		nextTurnButton.setFocusPainted(false);
		add(nextTurnButton);
		setVisible(true);
    }

    public void update() {
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
		nextTurnButton.setLocation(new Point(getSize().width - 150, 10));
    	int startX = (getSize().width - 575)/2;
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("SETTLERS", 10, 30);
    	for (int i = 0; i < 6; i++) {
    		g.drawImage(settlerImages.get(i),startX +i*100,50,null);
		}
	}
}
