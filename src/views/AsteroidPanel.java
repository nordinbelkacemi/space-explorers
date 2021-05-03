package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import controllers.Game;
import models.playfield.Asteroid;

public class AsteroidPanel extends UpdatablePanel{

    private JLabel thickness;
    private JLabel core;
    private List<JLabel> travelerLabels;
    private Asteroid asteroid;

    public AsteroidPanel() {
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setPreferredSize(new Dimension(250,300));
		setVisible(true);
    }

    public void update() {
        asteroid = Game.getInstance().getSelectedAsteroid();
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
	}
}
