package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.SelectedSettler;

public class PlaceTeleportGateButton extends ActionButton {
    public PlaceTeleportGateButton() {
        super("place teleport gate");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().placeTeleportGate();
            }
        });
    }
}
