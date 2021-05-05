package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import controller.Game;
import model.playfield.Asteroid;

public class AsteroidPanel extends GamePanel{

    private JLabel thickness;
    private JLabel core;
    private List<JLabel> travelerLabels;
    private Asteroid asteroid;
    private int index;
	
	private List<BufferedImage> materialImages = new ArrayList<>();
	private List<String> materialImagePaths = new ArrayList<>(Arrays.asList(
		"res/ice.png",
		"res/coal.png",
		"res/uranium.png",
		"res/iron.png"
	));
	private int ice = 0, coal = 1, uranium = 2, iron = 3;

    public AsteroidPanel() {
    	super(new Dimension(250,300));
		loadImages(materialImages, materialImagePaths);
		setVisible(true);
    }

    public void update() {
        asteroid = Game.getInstance().getSelectedAsteroid();
        if (asteroid != null) {
            index = Game.getInstance().getSelectedField().getAsteroids().indexOf(asteroid);
        }
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("ASTEROID", 10, 30);
		
    	if(asteroid != null) {
    		g.drawString("" + index, getSize().width - 50, 30);
    		g.setFont(new Font(getFont().getFontName(), Font.BOLD, 15));
			
    		if(asteroid.getMaterial() != null) {
				String material = asteroid.getMaterial().toString();
    			g.drawString("Core: " + material, 15, 70);
				BufferedImage materialImage = null;
				switch (material) {
					case "ice":
						materialImage = materialImages.get(ice);
						break;
					case "coal":
						materialImage = materialImages.get(coal);
						break;
					case "uranium":
						materialImage = materialImages.get(uranium);
						break;
					case "iron":
						materialImage = materialImages.get(iron);
						break;
					default:

				}
				g.drawImage(materialImage, 150, 50, null);
    		} else {
    			g.drawString("Core: empty", 15, 70);
			}
    		g.drawString("Thickness: " + asteroid.getThickness(), 15, 100);
    	}
	}
}
