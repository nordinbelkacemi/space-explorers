package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controller.Game;
import controller.SelectedSettler;
import model.playfield.AsteroidField;
import model.playfield.Coordinate;
import model.playfield.SolarSystem;
import model.settler.Settler;

public class SpacePanel extends GamePanel {

    private SolarSystem solarSystem;

	private List<BufferedImage> images = new ArrayList<>();
	private List<String> imagePaths = new ArrayList<>(Arrays.asList(
		"res/redicon.png",
		"res/blueicon.png",
		"res/greenicon.png",
		"res/yellowicon.png",
		"res/purpleicon.png",
		"res/orangeicon.png",
		"res/sunfield.png",
		"res/field.png",
		"res/sun.png",
		"res/hexagon.png",
		"res/hexagon2.png"
	));
	int sunFieldImg = 6, fieldImg = 7, sunImg = 8, hexagonImg = 9, hexagon2Img = 10;

    private List<AsteroidField> belt;
	private List<JPanel> settlerIconPanels;

    public SpacePanel() {
    	super(null);
    	loadImages(images, imagePaths);

    	addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (!Game.getInstance().isGameOver()) {
					int xc = (int) e.getPoint().getX(), yc = (int) e.getPoint().getY();
					for (int i = 0; i < belt.size(); i++) {
						Coordinate co = belt.get(i).getCo();
						int x = (getSize().width / 2 + co.getX() * 50 + co.getY() * 25);
						int y = (int) (getSize().height / 2 - co.getY() * Math.sqrt(3) * 25);
						if (Math.pow(x - xc, 2) + Math.pow(y - yc, 2) < 1600) {
							Game.getInstance().selectField(i);
							break;
						}
					}
				}
			}
		});
		setLayout(null);
		initSettlerIconPanels();
		setKeyBindings();
		update();
		setVisible(true);
    }

	private void initSettlerIconPanels() {
		settlerIconPanels = new ArrayList<>();

		for (int i = 0; i < 6; i++) {
			SettlerIconPanel settlerIconPanel = new SettlerIconPanel(images.get(i));
			settlerIconPanels.add(settlerIconPanel);
			add(settlerIconPanel);
		}

		updateSettlerIconPanels();
	}

    public void update() {
        solarSystem = Game.getInstance().getSolarSystem();
        belt = solarSystem.getBelt();
        repaint();
    }

    private void paintNeighbours(Graphics g) {
    	Settler settler = SelectedSettler.getInstance().getSelectedSettler();
    	if (settler != null) {
    		List<AsteroidField> neighbours = settler.getNeighbours();
    		for (AsteroidField field : neighbours) {
    			Coordinate co = field.getCo();
    			int x = (getSize().width / 2 + co.getX() * 50 + co.getY() * 25) - 30;
    			int y = (int) (getSize().height / 2 - co.getY() * Math.sqrt(3) * 25) - 30;
    			if(field != settler.getAsteroid().getAsteroidField()) {
    				g.drawImage(images.get(hexagonImg), x, y, null);
				} else {
    				g.drawImage(images.get(hexagon2Img), x, y, null);
				}
    		}
    	}
    }

    private void paintFields(Graphics g) {
    	Coordinate sunCo = solarSystem.getSun().getCo();
    	int sx = getSize().width / 2 + sunCo.getX() * 50 + sunCo.getY() * 25 - 20;
		int sy = (int) (getSize().height / 2 - sunCo.getY() * Math.sqrt(3) * 25) - 20;
		g.drawImage(images.get(sunImg), sx, sy, null);
    	for (AsteroidField field : belt) {
			Coordinate co = field.getCo();
			int x = getSize().width / 2 + co.getX() * 50 + co.getY() * 25 - 20;
			int y = (int) (getSize().height / 2 - co.getY() * Math.sqrt(3) * 25) - 20;
			int diffX = sunCo.getX() - co.getX();
			int diffY = sunCo.getY() - co.getY();
			if (Math.abs(diffX) <= 2 && Math.abs(diffY) <= 2 && Math.abs(diffX + diffY) <= 2) {
					g.drawImage(images.get(sunFieldImg), x, y, null);
			} else {
				g.drawImage(images.get(fieldImg), x, y, null);
			}
		}
    }

    private Coordinate pre = null;

    private void paintSolarFlare(Graphics g) {
    	Coordinate flare = solarSystem.getSun().getFlareDir();
    	if(flare == null)
    		pre = null;
    	else if(flare != pre) {
    		Coordinate sunCo = solarSystem.getSun().getCo();
        	int sx = (getSize().width / 2 + sunCo.getX() * 50 + sunCo.getY() * 25);
    		int sy = (int) (getSize().height/2 - sunCo.getY() * Math.sqrt(3) * 25);
    		int fx =  flare.getX() * 50 + flare.getY() * 25;
    		int fy =  (int) (flare.getY() * Math.sqrt(3) *- 25);
            Color startColor = new Color(0.96f, 0.82f, 0.19f, 0.7f);
            Color endColor = new Color(0.96f, 0.96f, 0.23f, 0.1f);
    		GradientPaint gp = new GradientPaint(sx + fx / 2, sy + fy / 2, startColor, sx + fx * 20, sy + fy * 20, endColor, false);
    		Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(30));
    		g2.setPaint(gp);
    		g2.drawLine(sx + fx / 2, sy + fy / 2, sx + fx * 20, sy + fy * 20);

    		pre = flare;
    	}
    }

    public void paint(Graphics g) {
    	super.paint(g);
		if (!Game.getInstance().isGameOver()) {
			paintNeighbours(g);
			paintFields(g);
			paintSolarFlare(g);
			updateSettlerIconPanels();
			for (int i = 0; i < 6; i++) {
				settlerIconPanels.get(i).repaint();
			}
		} else {
			String endText;
			if (Game.getInstance().playerLost()) {
				endText = "YOU LOST";
			} else {
				endText = "YOU WON";
			}
			g.setFont(new Font(getFont().getFontName(), Font.BOLD, 60));
			int length = g.getFontMetrics().stringWidth(endText);
			int x = getSize().width / 2 - length / 2;
			int y = getSize().height / 2 - getFont().getSize() / 2;
			g.drawString(endText, x, y);
		}
	}

	private void updateSettlerIconPanels() {
		int i = 0;
		for (Settler settler : Game.getInstance().getSettlers()) {
			Coordinate co = settler.getAsteroid().getAsteroidField().getCo();
			int x = (getSize().width / 2 + co.getX() * 50 + co.getY() * 25) - 25 / 2;
			int y = (int) (getSize().height / 2 - co.getY() * Math.sqrt(3) * 25) - 25 / 2;
			settlerIconPanels.get(i++).setBounds(x, y, 25, 25);
		}
	}

	private void setKeyBindings() {
		for (int i = 0; i < 6; i++) {
			int key = KeyEvent.VK_1 + i;

			boolean onKeyRelease = true;
			addKeyBind(KeyStroke.getKeyStroke(key, 0), key, !onKeyRelease);
			addKeyBind(KeyStroke.getKeyStroke(key, 0, true), key, onKeyRelease);
		}
	}

	private void addKeyBind(KeyStroke keyStroke, int key, boolean onKeyRelease) {
		String name;
		if (onKeyRelease) {
			name = new String("released" + key);
		} else {
			name = new String("pressed" + key);
		}

		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(keyStroke, name);
		getActionMap().put(name, new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				if (onKeyRelease) {
					keyReleased(key);
				} else {
					keyPressed(key);
				}
			}
		});
	}

	private void keyPressed(int key) {
		int i = key - KeyEvent.VK_0;
		List<Settler> settlers = Game.getInstance().getSettlers();
		if (settlers.stream().anyMatch(settler -> settler.getId() == i)) {
			settlerIconPanels.get(i - 1).setVisible(true);
		}
	}

	private void keyReleased(int key) {
		int i = key - KeyEvent.VK_0;
		List<Settler> settlers = Game.getInstance().getSettlers();
		if (settlers.stream().anyMatch(settler -> settler.getId() == i)) {
			settlerIconPanels.get(i - 1).setVisible(false);
		}
	}
}
