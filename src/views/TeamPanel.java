package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class TeamPanel extends UpdatablePanel {

    private JButton nextTurnButton;

    public TeamPanel() {
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setPreferredSize(new Dimension(300,150));
		setVisible(true);
    }

    public void update() {
        /* TODO TeamPanel.update */
    }
}
