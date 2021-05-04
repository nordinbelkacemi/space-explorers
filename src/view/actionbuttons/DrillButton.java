package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.SelectedSettler;
import view.GameButton;

public class DrillButton extends GameButton {
    public DrillButton() {
        super("drill");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().drill();
            }
        });
    }
}
