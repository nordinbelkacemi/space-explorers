package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import controllers.Game;
import model.playfield.AsteroidField;

public class FieldPanel extends UpdatablePanel {
    private List<JLabel> asteroids;
    private List<JLabel> teleportGates;
    private AsteroidField field;
    private int index;

    public FieldPanel() {
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setPreferredSize(new Dimension(250,300));
		setVisible(true);
		update();
    }

    public void update() {
        field = Game.getInstance().getSelectedField();
        if (field != null) {
            index = Game.getInstance().getSolarSystem().getBelt().indexOf(field);
        }
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
	}
}
