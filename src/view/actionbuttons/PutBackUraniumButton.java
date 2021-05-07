package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.SelectedSettler;
import view.GameButton;

public class PutBackUraniumButton extends GameButton {
    public PutBackUraniumButton() {
        super("put uranium back");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().putUraniumBack();
            }
        });
    }
}
