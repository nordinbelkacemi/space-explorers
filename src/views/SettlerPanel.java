package views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import models.settler.Settler;

public class SettlerPanel extends UpdatablePanel {
    private List<JLabel> inventory;
    private List<JButton> actionButtons;
    private Settler settler;
    
    SettlerPanel() {
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setPreferredSize(new Dimension(250,600));
		setVisible(true);
    }

    public void update() {
        /* TODO SettlerPanel.update */
    }
}
