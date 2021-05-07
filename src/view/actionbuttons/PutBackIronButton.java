package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.SelectedSettler;
import view.GameButton;

public class PutBackIronButton extends GameButton {
    public PutBackIronButton() {
        super("put iron back");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().putIronBack();
            }
        });
    }
}
