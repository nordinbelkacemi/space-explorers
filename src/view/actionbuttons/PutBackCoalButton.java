package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.SelectedSettler;
import view.GameButton;

public class PutBackCoalButton extends GameButton {
    public PutBackCoalButton() {
        super("putback coal");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().putCoalBack();
            }
        });
    }
}
