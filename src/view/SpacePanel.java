package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import controller.Game;
import controller.SelectedSettler;
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
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
		update();
		setVisible(true);
    }

    public void update() {
        solarSystem = Game.getInstance().getSolarSystem();
        belt = solarSystem.getBelt();
        repaint();
    }

    private void paintNeighbours(Graphics g) {
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
    }

    private void paintFields(Graphics g) {
    	Coordinate sunCo = solarSystem.getSun().getCo();
    	int sx = getSize().width / 2 + sunCo.getX() * 50 + sunCo.getY() * 25 - 20;
		int sy = (int) (getSize().height / 2 - sunCo.getY() * Math.sqrt(3) * 25) - 20;
		g.drawImage(sunImg, sx, sy, null);
    	for (AsteroidField field : belt) {
			Coordinate co = field.getCo();
			int x = getSize().width / 2 + co.getX() * 50 + co.getY() * 25 - 20;
			int y = (int) (getSize().height / 2 - co.getY() * Math.sqrt(3) * 25) - 20;
			int diffX = sunCo.getX() - co.getX();
			int diffY = sunCo.getY() - co.getY();
			if (Math.abs(diffX) <= 2 && Math.abs(diffY) <= 2 && Math.abs(diffX + diffY) <= 2) {
					g.drawImage(sunFieldImg, x, y, null);
				}
			else {
				g.drawImage(fieldImg, x, y, null);
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
        	int sx = (getSize().width/2 + sunCo.getX()*50+sunCo.getY()*25);
    		int sy = (int) (getSize().height/2 - sunCo.getY()*Math.sqrt(3)*25);
    		int fx =  flare.getX()*50+flare.getY()*25;
    		int fy =  (int) (flare.getY()*Math.sqrt(3)*-25);
            Color startColor = new Color(0.96f, 0.82f, 0.19f, 0.7f);
            Color endColor = new Color(0.96f, 0.96f, 0.23f, 0.1f);
    		GradientPaint gp = new GradientPaint(sx+fx/2, sy+fy/2, startColor, sx+fx*20, sy+fy*20, endColor, false);
    		Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(30));
    		g2.setPaint(gp);
    		g2.drawLine(sx+fx/2, sy+fy/2, sx+fx*20, sy+fy*20);

    		pre = flare;
    	}
    }

    public void paint(Graphics g) {
    	super.paint(g);
		if (!Game.getInstance().isGameOver()) {
			paintNeighbours(g);
			paintFields(g);
			paintSolarFlare(g);
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
}
