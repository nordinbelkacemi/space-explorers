package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;


public class TeamPanel extends UpdatablePanel {

    List<BufferedImage> settlerImages;
    private JButton nextTurnButton;
    JLabel teamPanelLabel;

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
