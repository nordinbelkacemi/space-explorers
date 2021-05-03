package views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import models.playfield.AsteroidField;

public class FieldPanel extends UpdatablePanel {
    private List<JLabel> asteroids;
    private List<JLabel> teleportGates;
    private AsteroidField field;

    public FieldPanel() {
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setPreferredSize(new Dimension(250,300));
		setVisible(true);
    }

    public void update() {
        /* TODO FieldPanel.update() */
    }
}
