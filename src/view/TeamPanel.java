package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import controllers.Game;
import model.settler.Settler;


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
    	
    	addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int x = (int) e.getPoint().getX(), y = (int) e.getPoint().getY();
				int startX = (getSize().width - 575) / 2;
				if (y < 125 && y > 50 && x < startX + 575 && x > startX) {
					x -= startX;
					x /= 96;
					Settler settler = selectableSettlers.get(x);
					if (x < selectableSettlers.size() && settler != null)
						Game.getInstance().selectSettler(settler.getId());
				}
			}
		});
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setForeground(Color.white);
		setPreferredSize(new Dimension(300,150));
		nextTurnButton = new GameButton("next turn");
        nextTurnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Game.getInstance().endTurn();
            }
		});

		add(nextTurnButton);
		
		setVisible(true);
		update();
    }

    public void update() {
    	selectableSettlers = Game.getInstance().getSelectableSettlers();
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	nextTurnButton.setLocation(getSize().width - 150, 10);
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
