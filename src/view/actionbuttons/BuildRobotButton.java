package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.SelectedSettler;
import view.GameButton;

public class BuildRobotButton extends GameButton {
    public BuildRobotButton() {
        super("build robot");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().buildRobot();
            }
        });
    }
}
