package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.SelectedSettler;
import view.GameButton;

public class MoveButton extends GameButton {
    public MoveButton() {
        super("move");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().move();
            }
        });
    }
}
