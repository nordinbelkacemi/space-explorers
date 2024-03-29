package model.ai;

import java.util.ArrayList;
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
	
	private Random r = new Random();
	
	public UfoAi(List<AsteroidField> b) {
		Ufo.setAi(this);
		ufos = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			ufos.add(new Ufo(b.get(r.nextInt(42)).getAsteroids().get(0)));
		}
	}
	
	/**
	* Meghatározza az ufok következő lépését és végre is hajtja azokat.
	*/
	public void control() {
		for (Ufo u : ufos) {
			if (!u.getAsteroid().isEmpty() && u.getAsteroid().getThickness() == 0) {
				u.mine();
				return;
			}
			var neighbours = u.getNeighbours();
			AsteroidField aF = neighbours.get(r.nextInt(neighbours.size()));
			Asteroid a = aF.getAsteroids().get(r.nextInt(aF.getAsteroids().size()));
			u.move(a);
		}
	}
	
	public void remove(Ufo r) {
		ufos.remove(r);
	}
}
