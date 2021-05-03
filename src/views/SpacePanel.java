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
    private BufferedImage sunFieldImg;
    private BufferedImage fieldImg;
    private BufferedImage sunImg;
    private List<AsteroidField> belt;
    public SpacePanel() {
    	try {
    		InputStream in = new FileInputStream("res/sunfield.png");
			sunFieldImg = ImageIO.read(in);
			in = new FileInputStream("res/field.png");
			fieldImg = ImageIO.read(in);
			in = new FileInputStream("res/sun.png");
			sunImg = ImageIO.read(in);
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
    	Coordinate sunCo = solarSystem.getSun().getCo();
    	for (AsteroidField field : belt) {
			Coordinate co = field.getCo();
			int x = (getSize().width/2 + co.getX()*50+co.getY()*25)-20;
			int y = (int) (getSize().height/2 + co.getY()*Math.sqrt(3)*25) -20;
			
			if (Math.abs(co.getX() - sunCo.getX()) <= 2 && Math.abs(co.getY() - sunCo.getY()) <= 2)
				g.drawImage(sunFieldImg,x,y,null);
			else
				g.drawImage(fieldImg,x,y,null);
		}
    	int x = (getSize().width/2 + sunCo.getX()*50+sunCo.getY()*25)-20;
		int y = (int) (getSize().height/2 + sunCo.getY()*Math.sqrt(3)*25) -20;
		g.drawImage(sunImg,x,y,null);
	}
}
