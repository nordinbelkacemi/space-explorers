package view;

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

import javax.swing.JButton;

import controller.Game;
import controller.SelectedSettler;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;
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

	private List<BufferedImage> materialImages = new ArrayList<>();
	private List<String> materialImagePaths = new ArrayList<>(Arrays.asList(
		"res/icesmall.png",
		"res/coalsmall.png",
		"res/uraniumsmall.png",
		"res/ironsmall.png"
	));

	private int ice = 0, coal = 1, uranium = 2, iron = 3;

	/** amit lehet csinalni eppen */
	private List<String> actions;

	/** minden lehetoseg */
	private List<String> allActions;

	private HashMap<String, GameButton> actionButtons = new HashMap<>();

	private GameButton showButton;
    SettlerPanel() {
		super(new Dimension(300,600));
		loadImages(settlerImages, settlerImagePaths);
		loadImages(materialImages, materialImagePaths);
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

    private void initButtons() {
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
    	
    	showButton = new GameButton("Show asteroid");
		showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Asteroid asteroid = settler.getAsteroid();
            	AsteroidField field = asteroid.getAsteroidField();
            	List<AsteroidField> belt = Game.getInstance().getSolarSystem().getBelt();
                Game.getInstance().selectField(belt.indexOf(field));
                Game.getInstance().selectAsteroid(field.getAsteroids().indexOf(asteroid));
            }
        });
		add(showButton);
    	clearButtons();
    }
    
    private void clearButtons() {
    	for (String action : actionButtons.keySet()) {
			JButton button = actionButtons.get(action);
			button.setVisible(false);
		}
    	showButton.setVisible(false);
    }
    
    private void placeButtons() {
    	for (int i = 0; i < 3; i++) {
			actionButtons.get(allActions.get(i)).setLocation(15+i*100, getSize().height/2 + 60);
		}
    	for (int i = 3; i < 6; i++) {
    		actionButtons.get(allActions.get(i)).setLocation(15, getSize().height/2 + (i-3)*35 + 110);
		}
    	for (int i = 6; i < 10; i++) {
    		actionButtons.get(allActions.get(i)).setLocation(15, getSize().height/2 + (i-6)*35 + 240);
		}
		showButton.setLocation(15,220);
    }
    
    private void updateAvailableButtons() {
    	actions = SelectedSettler.getInstance().getActions();
    	
		for (String action : actions) {
			JButton button = actionButtons.get(action);
			button.setVisible(true);
		}
		showButton.setVisible(true);
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

    	if (settler != null) {
	    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 20));
	    	g.drawString("Can do:", 10, getSize().height/2+50);
	    	g.drawString("Inventory:", 10, 280);
	    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 12));

			g.drawImage(materialImages.get(ice), 15, 300, null);
			g.drawImage(materialImages.get(coal), 15, 325, null);
			g.drawImage(materialImages.get(uranium), 15, 350, null);
			g.drawImage(materialImages.get(iron), 15, 375, null);

	    	g.drawString("x" + settler.getIceCount(), 40, 315);
	    	g.drawString("x" + settler.getCoalCount(), 40, 340);
	    	g.drawString("x" + settler.getUraniumCount(), 40, 365);
	    	g.drawString("x" + settler.getIronCount(), 40, 390);

			int id = settler.getId();
			g.drawImage(settlerImages.get(id - 1), 100, 50, null);
		}
	}
}
