package view;

import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.Game;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;

public class FieldPanel extends GamePanel {
    //private List<AsteroidButton> asteroidButtons = new ArrayList<>();
	private List<AsteroidPanel> asteroidPanels = new ArrayList<>();
    private AsteroidField field;
    private int index, gateCount;
	
	private List<BufferedImage> images = new ArrayList<>();
	private List<String> imagePaths = new ArrayList<>(Arrays.asList(
		"res/gate.png"
	));
	private int teleportGate = 0;
	
    public FieldPanel() {
    	super(new Dimension(300,300));
		setVisible(true);
		loadImages(images, imagePaths);
		initAsteroids();
		update();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (!Game.getInstance().isGameOver()) {
					int yc = (int) e.getPoint().getY();
					if (yc > 50) {
						int index = (yc - 50) / 108;
						if (((yc - 150) - index * 108) < 0 && index < field.getAsteroids().size()) {
							Game.getInstance().selectAsteroid(index);
						}
					}
				}
			}
		});
    }

    private void initAsteroids() {
    	for (int i = 0; i < 9; i++) {
    		AsteroidPanel panel = new AsteroidPanel();
			asteroidPanels.add(panel);
			add(panel);
			clearAsteroids();
		}
    	
    }
    
    private void clearAsteroids() {
		for (AsteroidPanel panel : asteroidPanels) {
			panel.setVisible(false);
		}
	}
    
    private void placeAsteroidPanels() {
    	for (int i = 0; i < 9; i++) {
			asteroidPanels.get(i).setLocation(0,50+i*108);
		}
	}
    
    private void updateAsteroidPanels() {
        List<Asteroid> asteroids = field.getAsteroids();
        for (int i = 0; i < asteroids.size(); i++) {
			asteroidPanels.get(i).setVisible(true);
		}
    }
    
    public void update() {
        field = Game.getInstance().getSelectedField();
        if (field != null) {
            index = Game.getInstance().getSolarSystem().getBelt().indexOf(field);
			gateCount = field.getGates().size();
        }
    	clearAsteroids();
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("FIELD", 10, 36);
    	if(field != null) {
			placeAsteroidPanels();
    		updateAsteroidPanels();
    		g.drawString("" + index, 105, 36);

			g.drawImage(images.get(teleportGate), 190, 0, null);
			g.drawString("x" + gateCount, 245, 36);
    	}
	}
}
