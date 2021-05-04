package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.SelectedSettler;

public class BuildTeleportGateButton extends ActionButton {
    public BuildTeleportGateButton() {
        super("build teleport gate");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().buildTeleportGate();
            }
        });
    }
}
