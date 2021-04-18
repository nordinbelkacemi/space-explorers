package model.ai;

import java.util.List;
import java.util.Random;

import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.playfield.Ufo;

/**
 * Az ufok tárolásáért és irányításáért felelős objektum.
 */
public class UfoAi implements Ai {

	/**
	 * A settlerek által épített és AI által vezérelt robotokat tartalmazza.
	 */
	private List<Ufo> ufos;

	/**
	* Meghatározza az ufok következő lépését és végre is hajtja azokat.
	*/
	public void control() {
		Random r = new Random();
		for (Ufo u : ufos) {
			if (!u.getAsteroid().isEmpty() && u.getAsteroid().getThickness() > 0) {
				u.mine();
				return;
			}
			var neighbours = u.getNeighbours();
			AsteroidField aF = neighbours.get(r.nextInt(neighbours.size() - 1));
			Asteroid a = aF.getAsteroids().get(r.nextInt(aF.getAsteroids().size() - 1));
			u.move(a);
		}
	}
}
