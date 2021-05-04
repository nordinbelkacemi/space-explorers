package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.SelectedSettler;
import view.GameButton;

public class PutBackIceButton extends GameButton {
    public PutBackIceButton() {
        super("putback ice");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().putIceBack();
            }
        });
    }
}
