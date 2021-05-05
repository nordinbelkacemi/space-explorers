package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.SelectedSettler;
import view.GameButton;

public class BuildTeleportGateButton extends GameButton {
    public BuildTeleportGateButton() {
        super("build teleport gate");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().buildTeleportGate();
            }
        });
    }
}
