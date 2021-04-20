package model.ai;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.playfield.Ufo;
import model.settler.Settler;
import model.settler.buildable.Robot;

/**
 * A robotok tárolásáért és irányításáért felelős objektum.
 */
public class RobotAi implements Ai {

	/**
	 * A settlerek által épített és AI által vezérelt robotokat tartalmazza.
	 */
	private List<Robot> robots;
	
	private Random ran = new Random();

	public RobotAi() {
		Robot.setAi(this);
		robots = new ArrayList<>();
	}
	
	/**
	* Meghatározza a robotok következő lépését és végre is hajtja azokat.
	*/
	public void control() {
		for (Robot r : robots) {
			if (!r.getAsteroid().isEmpty() && r.getAsteroid().getThickness() > 0) {
				r.drill();
				return;
			}
			var neighbours = r.getNeighbours();
			AsteroidField aF = neighbours.get(ran.nextInt(neighbours.size()));
			Asteroid a = aF.getAsteroids().get(ran.nextInt(aF.getAsteroids().size()));
			r.move(a);
		}
	}

	public void addRobot(Robot r) {
		robots.add(r);
	}
	
	public void remove(Robot r) {
		robots.remove(r);
	}
	
	public void configOut(PrintStream out) {
		for (Robot r : robots) {
			r.printToConfig(out);
			out.println();
		}
	}
	
	///////////////////////////////////////// test
	public void addRobot(String s,List<AsteroidField> belt) {
		String[] asteroid = s.split(",");
		robots.add(new Robot(belt.get(Integer.parseInt(asteroid[0])).getAsteroids().get(Integer.parseInt(asteroid[1]))));
	}
}
