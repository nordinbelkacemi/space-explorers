package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class LogPanel extends UpdatablePanel {

    private JTextField log;

    public LogPanel() {
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setPreferredSize(new Dimension(300,150));
		setVisible(true);
    }

    public void update() {
        /* TODO LogPanel.update */
    }
}
