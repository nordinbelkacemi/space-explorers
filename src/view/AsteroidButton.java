package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.Game;

public class AsteroidButton extends GameButton {
	int index;
	public AsteroidButton(String text,int i) {
		super(text);
		index = i;
		addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().selectAsteroid(index);
            }
        });
	}
}
