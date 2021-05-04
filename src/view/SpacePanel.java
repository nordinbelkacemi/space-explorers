package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import controllers.Game;
import controllers.SelectedSettler;
import model.playfield.AsteroidField;
import model.playfield.Coordinate;
import model.playfield.SolarSystem;
import model.settler.Settler;

public class SpacePanel extends GamePanel {

    private SolarSystem solarSystem;
    private BufferedImage sunFieldImg;
    private BufferedImage fieldImg;
    private BufferedImage sunImg;
    private BufferedImage hexagonImg;
    private BufferedImage hexagon2Img;
    private List<AsteroidField> belt;
    public SpacePanel() {
    	super(null);
    	try {
    		InputStream in = new FileInputStream("res/sunfield.png");
			sunFieldImg = ImageIO.read(in);
			in = new FileInputStream("res/field.png");
			fieldImg = ImageIO.read(in);
			in = new FileInputStream("res/sun.png");
			sunImg = ImageIO.read(in);
			in = new FileInputStream("res/hexagon.png");
			hexagonImg = ImageIO.read(in);
			in = new FileInputStream("res/hexagon2.png");
			hexagon2Img = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
		});
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
    	Settler settler = SelectedSettler.getInstance().getSelectedSettler();
    	if(settler != null) {
    		List<AsteroidField> neighbours = settler.getNeighbours();
    		for (AsteroidField field : neighbours) {
    			Coordinate co = field.getCo();
    			int x = (getSize().width/2 + co.getX()*50+co.getY()*25)-30;
    			int y = (int) (getSize().height/2 - co.getY()*Math.sqrt(3)*25) -30;
    			if(field != settler.getAsteroid().getAsteroidField())
    				g.drawImage(hexagonImg,x,y,null);
    			else
    				g.drawImage(hexagon2Img,x,y,null);
    		}
    	}
    	
    	Coordinate sunCo = solarSystem.getSun().getCo();
    	//double SX = sunCo.getX() + sunCo.getY() / 2.0f;
		//double SY = sunCo.getY() * Math.sqrt(3) / 2.0f;
    	int sx = (getSize().width/2 + sunCo.getX()*50+sunCo.getY()*25)-20;
		int sy = (int) (getSize().height/2 - sunCo.getY()*Math.sqrt(3)*25) -20;
		g.drawImage(sunImg,sx,sy,null);
    	for (AsteroidField field : belt) {
			Coordinate co = field.getCo();
			int x = (getSize().width/2 + co.getX()*50+co.getY()*25)-20;
			int y = (int) (getSize().height/2 - co.getY()*Math.sqrt(3)*25) -20;
			//double X = co.getX() + co.getY() / 2.0f;
			//double Y = co.getY() * Math.sqrt(3) / 2.0f;
			int diffX = sunCo.getX() - co.getX();
			int diffY = sunCo.getY() - co.getY();
			if (Math.abs(diffX) <= 2 && Math.abs(diffY) <= 2 && Math.abs(diffX + diffY) <= 2) {
					g.drawImage(sunFieldImg,x,y,null);
				}
			else
				g.drawImage(fieldImg,x,y,null);
		}    	
	}
}
