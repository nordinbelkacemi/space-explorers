package model.playfield;

import java.util.ArrayList;
import java.util.List;

import model.settler.buildable.TeleportGate;

/**
 * A játéktér alapeleme; körben elhelyezve alkotják az aszteroidaövet, felelős a szomszédjaiért.
 * Az aszteroidákat tárolja.
 */
public class AsteroidField {

	/** Az AsteroidFieldhez tartozó aszteroidákat tárolja. */
	private List<Asteroid> asteroids;

	/** A szomszédos AsteroidFieldeket tartalmazza. */
	private List<AsteroidField> neighbours;

	/** Az AsteroidFielden elhelyezkedő teleportkapukat tárolja. */
	private List<TeleportGate> teleportGates;

	/**A megkergült kapukat tartalmazó osztály. */
	private static MegkergultGates megkergultGates;

	public AsteroidField() {
		asteroids = new ArrayList<>();
		neighbours = new ArrayList<>();
		teleportGates = new ArrayList<>();
	}

	/**
	 * Napvihar esetén hívódik meg, és minden aszteroidájára meghívja
	 * az Asteroid osztály ReactToFlare() függvényét.
	 */
	public void reactToFlare() {
	}

	/**
	 * Napközeli AsteroidFieldekre hívódik meg,
	 * és minden aszteroidájára meghívja a CheckDangers() függvényt.
	 */
	public void checkDangers() {
	}

	/**
	 * Visszaad egy, az aszteroidához tartozó AsteroidFieldet és annak szomszédait tartalmazó listát.
	 * @return a szomszédos aszteroidákat tartalmazó aszteroidamezők
	 */
	public List<AsteroidField> getNeighbours() {
		return null;
	}

	/**
	 * Visszaadja az asteroids tároló tartalmát.
	 * @return a mezőben elhelyezkedő aszteroidák
	 */
	public List<Asteroid> getAsteroids(){
		return asteroids;
	}

	/**
	 * Hozzáadja a paraméterként kapott kaput a teleportGates tárolóhoz.
	 * @param tg az új teleportkapu
	 */
	public void addTeleportGate(TeleportGate tg) {
	}

	/**
	 * Hozzáad egy AsteroidField-et a szomszédokat tároló neighbours-hoz.
	 * @param af új szomszéd
	 */
	public void addNeighbour(AsteroidField af){
	}

	/**
	 * Felvesz egy új aszteroidát a tárolóba.
	 * @param a új aszteroida
	 */
	public void AddAsteroid(Asteroid a) {
	}
}
