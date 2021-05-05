package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import controller.Game;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;

public class FieldPanel extends GamePanel {
    private List<AsteroidButton> asteroidButtons = new ArrayList<>();
    private List<JLabel> teleportGates;
    private AsteroidField field;
    private String index;

    public FieldPanel() {
    	super(new Dimension(250,300));
		setVisible(true);
		initButtons();
		update();
    }

    private void initButtons() {
    	for (int i = 0; i < 9; i++) {
    		AsteroidButton button = new AsteroidButton("Asteroid " + i, i);
			asteroidButtons.add(button);
			add(button);
			clearButtons();
		}
    	
    }
    
    private void clearButtons() {
		for (AsteroidButton button : asteroidButtons) {
			button.setVisible(false);
		}
	}
    
    private void placeButtons() {
    	for (int i = 0; i < 9; i++) {
			asteroidButtons.get(i).setLocation(15,50+i*30);
		}
	}
    
    private void updateAsteroidButtons() {
        List<Asteroid> asteroids = field.getAsteroids();
        for (int i = 0; i < asteroids.size(); i++) {
			asteroidButtons.get(i).setVisible(true);
		}
    }
    
    public void update() {
        field = Game.getInstance().getSelectedField();
        if (field != null) {
            index = String.valueOf(Game.getInstance().getSolarSystem().getBelt().indexOf(field));
        }
    	clearButtons();
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("FIELD", 10, 30);
    	if(field != null) {
    		placeButtons();
    		updateAsteroidButtons();
    		g.drawString(index, getSize().width-100, 30);
    	}
	}
}
