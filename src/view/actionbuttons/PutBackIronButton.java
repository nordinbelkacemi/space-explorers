package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.SelectedSettler;

public class PutBackIronButton extends ActionButton {
    public PutBackIronButton() {
        super("putback iron");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().putIronBack();
            }
        });
    }
}
