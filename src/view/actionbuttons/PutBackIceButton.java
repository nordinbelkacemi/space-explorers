package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.SelectedSettler;

public class PutBackIceButton extends ActionButton {
    public PutBackIceButton() {
        super("putback ice");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().putIceBack();
            }
        });
    }
}
