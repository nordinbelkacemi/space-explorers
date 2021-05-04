package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.SelectedSettler;

public class PutBackUraniumButton extends ActionButton {
    public PutBackUraniumButton() {
        super("putback uranium");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().putUraniumBack();
            }
        });
    }
}
