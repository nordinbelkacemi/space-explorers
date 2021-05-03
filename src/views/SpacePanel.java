package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import controllers.Game;
import models.playfield.AsteroidField;
import models.playfield.Coordinate;
import models.playfield.SolarSystem;

public class SpacePanel extends UpdatablePanel {

    private SolarSystem solarSystem;
    private BufferedImage sunField;
    private List<AsteroidField> belt;
    public SpacePanel() {
    	try {
    		InputStream in = new FileInputStream("res/sunfield.png");
			sunField = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		update();
		setVisible(true);
    }

    public void update() {
        solarSystem = Game.getInstance().getSolarSystem();
        belt = solarSystem.getBelt();
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	for (AsteroidField field : belt) {
			Coordinate co = field.getCo();
			int x = (getSize().width / 2 + co.getX() * 46 + co.getY() * 23) - 20;
			int y = (int) (getSize().height / 2 + co.getY() * Math.sqrt(3) * 23) - 20;
			g.drawImage(sunField, x, y, null);
		}
	}
}
