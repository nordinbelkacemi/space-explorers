package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

import controller.Game;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.settler.Settler;

public class TeamPanel extends GamePanel {

	private List<Settler> selectableSettlers;
    private JButton nextTurnButton;
	private List<BufferedImage> settlerImages = new ArrayList<>();
	private ArrayList<String> settlerImagePaths = new ArrayList<>(Arrays.asList(
		"res/redsmall.png",
		"res/bluesmall.png",
		"res/greensmall.png",
		"res/yellowsmall.png",
		"res/purplesmall.png",
		"res/orangesmall.png",
		"res/graysmall.png"
	));

    public TeamPanel() {
    	super(new Dimension(300,150));
		loadImages(settlerImages, settlerImagePaths);

    	addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int x = (int) e.getPoint().getX(), y = (int) e.getPoint().getY();
				int startX = (getSize().width - 575) / 2;
				if (y < 125 && y > 50 && x < startX + 575 && x > startX) {
					x -= startX;
					x /= 96;
					if (x < selectableSettlers.size()) {
						Settler settler = selectableSettlers.get(x);
						if(settler != null) {
							Asteroid asteroid = settler.getAsteroid();
			            	AsteroidField field = asteroid.getAsteroidField();
			            	List<AsteroidField> belt = Game.getInstance().getSolarSystem().getBelt();
			                Game.getInstance().selectField(belt.indexOf(field));
			                Game.getInstance().selectAsteroid(field.getAsteroids().indexOf(asteroid));
			                Game.getInstance().selectSettler(settler.getId());
						}
					}
				}
			}
		});
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
    	if (!Game.getInstance().isGameOver()) {
	    	nextTurnButton.setLocation(getSize().width - 150, 8);
	    	int startX = (getSize().width - 575)/2;
	    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
	    	g.drawString("SETTLERTEAM", 10, 30);
	    	for (int i = 0; i < selectableSettlers.size(); i++) {
	    		if(selectableSettlers.get(i) != null) {
		    		int imgIndex = selectableSettlers.get(i).getId();
		    		g.drawImage(settlerImages.get(imgIndex-1),startX +i*100,50,null);
		    	}
	    		else
	    			g.drawImage(settlerImages.get(6),startX +i*100,50,null);
	    	}
    	}
    	else {
    		nextTurnButton.setVisible(false);
    	}
	}
}
