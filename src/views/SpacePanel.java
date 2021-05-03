package views;

import java.awt.Color;

import javax.swing.BorderFactory;

import models.playfield.SolarSystem;

public class SpacePanel extends UpdatablePanel {

    private SolarSystem solarSystem;

    public SpacePanel() {
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setVisible(true);
    }

    public void update() {
        /* TODO SpacePanel.update */
    }
}
