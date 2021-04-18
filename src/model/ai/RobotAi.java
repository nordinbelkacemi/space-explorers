package model.ai;

import java.util.List;
import java.util.Random;

import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.settler.buildable.Robot;

/**
 * A robotok tárolásáért és irányításáért felelős objektum.
 */
public class RobotAi implements Ai {

	/**
	 * A settlerek által épített és AI által vezérelt robotokat tartalmazza.
	 */
	private List<Robot> robots;

	/**
	* Meghatározza a robotok következő lépését és végre is hajtja azokat.
	*/
public void control() {
		Random ran = new Random();
		for (Robot r : robots) {
			if (!r.getAsteroid().isEmpty() && r.getAsteroid().getThickness() > 0) {
				r.drill();
				return;
			}
			var neighbours = r.getNeighbours();
			AsteroidField aF = neighbours.get(ran.nextInt(neighbours.size() - 1));
			Asteroid a = aF.getAsteroids().get(ran.nextInt(aF.getAsteroids().size() - 1));
			r.move(a);
		}
	}

	public void addRobot(Robot r) {
		robots.add(r);
	}
}
