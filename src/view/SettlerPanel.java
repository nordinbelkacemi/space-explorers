package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import controllers.Game;
import controllers.SelectedSettler;
import model.settler.Settler;
import view.actionbuttons.BuildRobotButton;
import view.actionbuttons.BuildTeleportGateButton;
import view.actionbuttons.DrillButton;
import view.actionbuttons.MineButton;
import view.actionbuttons.MoveButton;
import view.actionbuttons.PlaceTeleportGateButton;
import view.actionbuttons.PutBackCoalButton;
import view.actionbuttons.PutBackIceButton;
import view.actionbuttons.PutBackIronButton;
import view.actionbuttons.PutBackUraniumButton;

public class SettlerPanel extends GamePanel {
    private List<JLabel> inventory;
    private Settler settler;
    private List<BufferedImage> settlerImages = new ArrayList<>();
	private ArrayList<String> settlerImagePaths = new ArrayList<>(Arrays.asList(
		"res/redbig.png",
		"res/bluebig.png",
		"res/greenbig.png",
		"res/yellowbig.png",
		"res/purplebig.png",
		"res/orangebig.png"
	));
	/** amit lehet csinalni eppen */
	private List<String> actions;

	/** minden lehetoseg */
	private List<String> allActions;

	private HashMap<String, GameButton> actionButtons = new HashMap<>();

    SettlerPanel() {
		super(new Dimension(300,600));
		loadImages(settlerImages, settlerImagePaths);
		initButtons();
		allActions = new ArrayList<>(Arrays.asList(
			"move",
			"drill",
			"mine",
			"build robot",
			"build teleportgate",
			"place teleportgate",
			"putback iron",
			"putback uranium",
			"putback coal",
			"putback ice"
		));
		setVisible(true);
		update();
    }

    public void initButtons() {
		actionButtons.put("move", new MoveButton());
		actionButtons.put("drill", new DrillButton());
		actionButtons.put("mine", new MineButton());
		actionButtons.put("build robot", new BuildRobotButton());
		actionButtons.put("build teleportgate", new BuildTeleportGateButton());
		actionButtons.put("place teleportgate", new PlaceTeleportGateButton());
		actionButtons.put("putback iron", new PutBackIronButton());
		actionButtons.put("putback uranium", new PutBackUraniumButton());
		actionButtons.put("putback coal", new PutBackCoalButton());
		actionButtons.put("putback ice", new PutBackIceButton());

		for (String action : actionButtons.keySet()) {
			JButton button = actionButtons.get(action);
			add(button);
		}
    	clearButtons();
    }
    
    public void clearButtons() {
    	for (String action : actionButtons.keySet()) {
			JButton button = actionButtons.get(action);
			button.setVisible(false);
		}
    }
    
    public void placeButtons() {
		/* TODO SettlerPanel.placeButtons */

    	for (int i = 0; i < 3; i++) {
			actionButtons.get(allActions.get(i)).setLocation(15+i*100, getSize().height/2 + 30);
		}
    	for (int i = 3; i < 6; i++) {
    		actionButtons.get(allActions.get(i)).setLocation(15, getSize().height/2+ 80 + (i-3)*40);
		}
    	for (int i = 6; i < 10; i++) {
    		actionButtons.get(allActions.get(i)).setLocation(15, getSize().height/2+ 210 + (i-6)*40);
		}
    }
    
    public void updateAvailableButtons() {
    	actions = SelectedSettler.getInstance().getActions();
    	
		for (String action : actions) {
			JButton button = actionButtons.get(action);
			button.setVisible(true);
		}
    }

    public void update() {
		settler = SelectedSettler.getInstance().getSelectedSettler();
		clearButtons();
        repaint();
    }
    
    public void paint(Graphics g) {
		super.paint(g);
		if(settler != null) {
			placeButtons();
			updateAvailableButtons();
		}
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("SETTLER", 10, 30);
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 20));
    	g.drawString("Can do:", 10, getSize().height/2);
		if (settler != null) {
			int id = settler.getId();
			g.drawImage(settlerImages.get(id - 1), 100, 50, null);
		}
	}
}
