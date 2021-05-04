package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import controllers.SelectedSettler;
import models.playfield.AsteroidField;
import models.settler.Settler;

public class SettlerPanel extends UpdatablePanel {
    private List<JLabel> inventory;
    private List<String> actions;
    private List<JButton> actionButtons = new ArrayList<>();
    private Settler settler;
    private List<BufferedImage> settlerImages = new ArrayList<>();

    SettlerPanel() {
    	try {
			InputStream in = new FileInputStream("res/redbig.png");
			settlerImages.add(ImageIO.read(in));

			in = new FileInputStream("res/bluebig.png");
			settlerImages.add(ImageIO.read(in));

			in = new FileInputStream("res/greenbig.png");
			settlerImages.add(ImageIO.read(in));

			in = new FileInputStream("res/yellowbig.png");
			settlerImages.add(ImageIO.read(in));

			in = new FileInputStream("res/purplebig.png");
			settlerImages.add(ImageIO.read(in));

			in = new FileInputStream("res/orangebig.png");
			settlerImages.add(ImageIO.read(in));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setForeground(Color.white);
		setPreferredSize(new Dimension(300,600));
		initButtons();
		setVisible(true);
		update();
    }

    public void initButtons() {
    	actionButtons.add(new GameButton("move"));
    	actionButtons.add(new GameButton("drill"));
    	actionButtons.add(new GameButton("mine"));
    	actionButtons.add(new GameButton("build robot"));
    	actionButtons.add(new GameButton("build teleportgate"));;
    	actionButtons.add(new GameButton("place teleportgate"));
    	actionButtons.add(new GameButton("putback iron"));
    	actionButtons.add(new GameButton("putback uranium"));
    	actionButtons.add(new GameButton("putback coal"));
    	actionButtons.add(new GameButton("putback ice"));
    	for (JButton button : actionButtons) {
			add(button);
		}
    	clearButtons();
    }
    
    public void clearButtons() {
    	for (JButton button : actionButtons) {
			button.setVisible(false);
		}
    }
    
    public void placeButtons() {
    	for (int i = 0; i < 3; i++) {
			actionButtons.get(i).setLocation(15+i*100, getSize().height/2 + 30);
		}
    	for (int i = 3; i < 6; i++) {
    		actionButtons.get(i).setLocation(15, getSize().height/2+ 80 + (i-3)*40);
		}
    	for (int i = 6; i < 10; i++) {
    		actionButtons.get(i).setLocation(15, getSize().height/2+ 210 + (i-6)*40);
		}
    }
    
    public void updateAvailableButtons() {
    	actions = SelectedSettler.getInstance().getActions();
    	
    	JButton button = actionButtons.get(0);
    	if (actions.contains("move"))
    		button.setVisible(true);
    	
    	button = actionButtons.get(1);
    	if (actions.contains("drill"))
    		button.setVisible(true);
    	
    	button = actionButtons.get(2);
    	if (actions.contains("mine"))
    		button.setVisible(true);
    	
    	button = actionButtons.get(3);
    	if (actions.contains("build robot"))
    		button.setVisible(true);
    	
    	button = actionButtons.get(4);
    	if (actions.contains("build teleportgate"))
    		button.setVisible(true);
    	
    	button = actionButtons.get(5);
    	if (actions.contains("place teleportgate"))
    		button.setVisible(true);
    	
    	button = actionButtons.get(6);
    	if (actions.contains("putback iron"))
    		button.setVisible(true);
    	
    	button = actionButtons.get(7);
    	if (actions.contains("putback uranium"))
    		button.setVisible(true);
    	
    	button = actionButtons.get(8);
    	if (actions.contains("putback coal"))
    		button.setVisible(true);
    	
    	button = actionButtons.get(9);
    	if (actions.contains("putback ice"))
    		button.setVisible(true);
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
