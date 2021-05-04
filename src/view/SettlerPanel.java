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
import view.actionbuttons.ActionButton;
import view.actionbuttons.MoveButton;

public class SettlerPanel extends GamePanel {
    private List<JLabel> inventory;
    private Settler settler;
    private List<BufferedImage> settlerImages = new ArrayList<>();
	private ArrayList<String> settlerImagePaths = new ArrayList<>(Arrays.asList(
		"res/redsmall.png",
		"res/bluesmall.png",
		"res/greensmall.png",
		"res/yellowsmall.png",
		"res/purplesmall.png",
		"res/orangesmall.png",
		"res/graysmall.png"
	));

	private List<String> actions;
	private HashMap<String, ActionButton> actionButtons = new HashMap<>();

    SettlerPanel() {
		loadImages(settlerImages, settlerImagePaths);

    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setForeground(Color.white);
		setPreferredSize(new Dimension(300,600));
		initButtons();
		setVisible(true);
		update();
    }

    public void initButtons() {
		actionButtons.put("move", new MoveButton());
		actionButtons.put("move", new MoveButton());
		actionButtons.put("drill", new MoveButton());
		actionButtons.put("mine", new MoveButton());
		actionButtons.put("build robot", new MoveButton());
		actionButtons.put("build teleportgate", new MoveButton());
		actionButtons.put("place teleportgate", new MoveButton());
		actionButtons.put("putback iron", new MoveButton());
		actionButtons.put("putback uranium", new MoveButton());
		actionButtons.put("putback coal", new MoveButton());
		actionButtons.put("putback ice", new MoveButton());

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
		List<String> allActions = new ArrayList<>(actionButtons.keySet());

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
