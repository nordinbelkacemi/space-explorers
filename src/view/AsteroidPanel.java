package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import controller.Game;
import model.playfield.Asteroid;

public class AsteroidPanel extends GamePanel{

    private JLabel thickness;
    private JLabel core;
    private List<JLabel> travelerLabels;
    private Asteroid asteroid;

    public AsteroidPanel() {
    	super(new Dimension(250,300));
		setVisible(true);
    }

    public void update() {
        asteroid = Game.getInstance().getSelectedAsteroid();
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("ASTEROID", 10, 30);
    	if(asteroid != null) {
    		g.setFont(new Font(getFont().getFontName(), Font.BOLD, 15));
    		if(asteroid.getMaterial() != null) {
    			g.drawString("Core: " + asteroid.getMaterial().toString(), 15, 70);
    		}
    		else
    			g.drawString("Core: empty", 15, 70);
    		g.drawString("Thickness: " + asteroid.getThickness(), 15, 100);
    	}
	}
}
