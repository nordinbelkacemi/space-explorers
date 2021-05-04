package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class GameButton extends JButton {

	public GameButton(String text) {
		super(text);
		setBackground(Color.black);
		setForeground(Color.white);
		setFont(new Font(getFont().getFontName(), Font.BOLD, 15));
		setMinimumSize(new Dimension(100,30));
		setFocusPainted(false);
	}
}
