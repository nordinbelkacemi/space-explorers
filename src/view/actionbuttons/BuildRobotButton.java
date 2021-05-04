package view.actionbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.SelectedSettler;

public class BuildRobotButton extends ActionButton {
    public BuildRobotButton() {
        super("build robot");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectedSettler.getInstance().buildRobot();
            }
        });
    }
}
