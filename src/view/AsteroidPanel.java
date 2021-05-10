package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.Game;
import model.materials.Material;
import model.playfield.Asteroid;
import model.settler.Traveler;

public class AsteroidPanel extends GamePanel {
    private Asteroid asteroid;
	
	private List<BufferedImage> images = new ArrayList<>();
	private List<String> imagePaths = new ArrayList<>(Arrays.asList(
		"res/redicon.png",
		"res/blueicon.png",
		"res/greenicon.png",
		"res/yellowicon.png",
		"res/purpleicon.png",
		"res/orangeicon.png",
		"res/roboticon.png",
		"res/ufoicon.png",
		"res/asteroid.png",
		"res/ice.png",
		"res/coal.png",
		"res/uranium.png",
		"res/uranium1.png",
		"res/uranium2.png",
		"res/iron.png",
		"res/empty.png"
	));

	private int robot = 6, ufo = 7, asteroidIcon = 8, ice = 9, coal = 10, uranium = 11, uranium1 = 12, uranium2 = 13, iron = 14, empty = 15;

    public AsteroidPanel() {
    	super(new Dimension(290,100));
		setBorder(null);
		loadImages(images, imagePaths);
		setVisible(true);
    }

    public void update() {
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
		setBackground(Color.BLACK);

    	if(asteroid != null) {
    		g.drawImage(images.get(asteroidIcon), 10 + 2, 2, null);
    		g.setFont(new Font(getFont().getFontName(), Font.BOLD, 15));
			displayMaterialInfo(g);
			displayTravelers(g);

			if (asteroid.equals(Game.getInstance().getSelectedAsteroid())) {
				setBackground(Color.DARK_GRAY);
			}
    	}
	}

	private void displayMaterialInfo(Graphics g) {
		g.drawString("Core:", 12 + 65 + 10, 20 + 5);
		g.drawString("Thickness: " + asteroid.getThickness(), 12 + 65 + 10, 65 + 2 - 10);

		if (asteroid.getMaterial() != null) {
			Material material = asteroid.getMaterial();
			BufferedImage materialImage = null;
			switch (material.toString().split(" ")[0]) {
				case "ice":
					materialImage = images.get(ice);
					break;
				case "coal":
					materialImage = images.get(coal);
					break;
				case "uranium":
					String exposureCount = material.toString().split(" ")[1];
					switch (exposureCount) {
						case "0":
							materialImage = images.get(uranium);
							break;
						case "1":
							materialImage = images.get(uranium1);
							break;
						case "2":
							materialImage = images.get(uranium2);
							break;
					}
					break;
				case "iron":
					materialImage = images.get(iron);
					break;
			}
			g.drawImage(materialImage, 140, 2, null);
		} else {
			g.drawImage(images.get(empty), 140, 2, null);
		}
	}

	private void displayTravelers(Graphics g) {
		int settlerCount = 0, robotCount = 0, ufoCount = 0;

		List<Traveler> travelers = asteroid.getTravelers();
		for (Traveler traveler : travelers) {
			String identifier = traveler.toString();
			switch (identifier) {
				case "settler":
					settlerCount += 1;
					int settlerId = traveler.getId();
					g.drawImage(images.get(settlerId - 1), 10 + 2 + 25 * (settlerCount - 1), getHeight() - 2 - 25, null);
					break;
				case "robot":
					robotCount += 1;
					break;
				case "ufo":
					ufoCount += 2;
					break;
			}
		}

		if (robotCount > 0) {
			g.drawImage(images.get(robot), getWidth() - 30 - 25, getHeight() * 1 / 3 - 25 / 2, null);
			g.drawString("x" + robotCount, getWidth() - 25, getHeight() * 1 / 3 + 20 / 2);
		}

		if (ufoCount > 0) {
			g.drawImage(images.get(ufo), getWidth() - 30 - 25, getHeight() * 2 / 3 - 25 / 2, null);
			g.drawString("x" + ufoCount, getWidth() - 25, getHeight() * 2 / 3 + 20 / 2);
		}
	}

	public void setAsteroid(Asteroid asteroid) {
		this.asteroid = asteroid;
	}
}
