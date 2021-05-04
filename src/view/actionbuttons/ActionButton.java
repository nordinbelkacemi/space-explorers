package view.actionbuttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class ActionButton extends JButton {

	public ActionButton(String text) {
		super(text);
		setBackground(Color.black);
		setForeground(Color.white);
		setBorderPainted(false);
		setFont(new Font(getFont().getFontName(), Font.BOLD, 15));
		setMinimumSize(new Dimension(100,30));
		setFocusPainted(false);
	}
}
