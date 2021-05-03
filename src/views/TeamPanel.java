package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import controllers.Game;
import controllers.SelectedSettler;
import models.settler.Settler;



public class TeamPanel extends UpdatablePanel {

	private List<Settler> selectableSettlers;
	
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
			
			in = new FileInputStream("res/graysmall.png");
			settlerImages.add(ImageIO.read(in));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				double x = e.getPoint().getX(), y = e.getPoint().getY();
				int startX = (getSize().width - 575)/2;
				if(y < 125 && y > 50 && x < startX+575 && x > startX) {
					x -= startX;
					x /= 96;
					if(x < selectableSettlers.size())
						Game.getInstance().selectSettler(selectableSettlers.get((int) x).getId());
				}
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
    	
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
		update();
		setVisible(true);
    }

    public void update() {
    	selectableSettlers = Game.getInstance().getSelectableSettlers();
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
		nextTurnButton.setLocation(new Point(getSize().width - 150, 10));
    	int startX = (getSize().width - 575)/2;
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("SETTLERS", 10, 30);
    	for (int i = 0; i < selectableSettlers.size(); i++) {
    		if(selectableSettlers.get(i) != null) {
	    		int imgIndex = selectableSettlers.get(i).getId();
	    		g.drawImage(settlerImages.get(imgIndex-1),startX +i*100,50,null);
	    	}
    		else
    			g.drawImage(settlerImages.get(6),startX +i*100,50,null);
    	}
	}
}
