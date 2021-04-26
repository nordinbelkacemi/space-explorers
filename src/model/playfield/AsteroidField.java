package model.playfield;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import model.materials.Coal;
import model.materials.Ice;
import model.materials.Iron;
import model.materials.Uranium;
import model.settler.buildable.TeleportGate;

/**
 * A játéktér alapeleme; körben elhelyezve alkotják az aszteroidaövet, felelős a szomszédjaiért.
 * Az aszteroidákat tárolja.
 */
public class AsteroidField extends Hexagon {

	///////////////////////////////////////// attribútumok

	/** Az AsteroidFieldhez tartozó aszteroidákat tárolja. */
	private List<Asteroid> asteroids;

	/** A szomszédos AsteroidFieldeket tartalmazza. */
	private List<AsteroidField> neighbours;

	/** Az AsteroidFielden elhelyezkedő teleportkapukat tárolja. */
	private List<TeleportGate> teleportGates;

	/** A megkergült kapukat tartalmazó osztály. */
	private static MegkergultGates megkergultGates;

	/** Az összes AsteroidField listája. */
	private static List<AsteroidField> belt;

	//////////////////////////////////////// konstruktorok
	public AsteroidField() {
		asteroids = new ArrayList<>();
		neighbours = new ArrayList<>();
		teleportGates = new ArrayList<>();
	}

	public AsteroidField(Coordinate c) {
		position = c;
		asteroids = createAsteroids();
		neighbours = new ArrayList<>();
		teleportGates = new ArrayList<>();
	}
	////////////////////////////////////////
	
	/**
	 * Véletlenszerűen generál egy aszteroidákból álló listát.
	 * @return a generált lista
	 */
	private List<Asteroid> createAsteroids() {
		Random r  = new Random();
		ArrayList<Asteroid> field = new ArrayList<>();

		int emptycount = r.nextInt(2) + 1; // egy vagy kettő üres
		for (int i = 0; i < emptycount; i++) {
			field.add(new Asteroid(r.nextInt(5),this));
		}

		int ironcount = r.nextInt(3); // 0-2 vas
		for (int i = 0; i < ironcount; i++) {
			field.add(new Asteroid(new Iron(),r.nextInt(5),this));
		}

		int icecount = r.nextInt(3); // 0-2 jég
		for (int i = 0; i < icecount; i++) {
			field.add(new Asteroid(new Ice(),r.nextInt(5),this));
		}

		int coalcount = r.nextInt(3); // 0-2 szén
		for (int i = 0; i < coalcount; i++) {
			field.add(new Asteroid(new Coal(),r.nextInt(5),this));
		}

		int uraniumcount = r.nextInt(2); // 0-1 urán
		for (int i = 0; i < uraniumcount; i++) {
			field.add(new Asteroid(new Uranium(),r.nextInt(5),this));
		}
		return field;
	}

	///////////////////////////////////////// függvények
	/**
	 * Napvihar esetén hívódik meg, és minden aszteroidájára meghívja
	 * az Asteroid osztály ReactToFlare() függvényét.
	 */
	public void reactToFlare() {
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).reactToFlare();
		}
	}

	/**
	 * Napközeli AsteroidFieldekre hívódik meg,
	 * és minden aszteroidájára meghívja a CheckDangers() függvényt.
	 */
	public void checkDangers() {
		Iterator<Asteroid> asteroidIter = asteroids.iterator();
		while (asteroidIter.hasNext()) {
			asteroidIter.next().checkDangers(asteroidIter);
		}
	}

	/**
	 * Visszaad egy, az aszteroidához tartozó AsteroidFieldet és annak szomszédait tartalmazó listát.
	 * @return a szomszédos aszteroidákat tartalmazó aszteroidamezők
	 */
	public ArrayList<AsteroidField> getNeighbours() {
		ArrayList<AsteroidField> neighbourFields = new ArrayList<AsteroidField>(neighbours);
		neighbourFields.add(0, this);
		return neighbourFields;
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
		teleportGates.add(tg);
	}

	/**
	 * Hozzáad egy AsteroidField-et a szomszédokat tároló neighbours-hoz.
	 * @param af új szomszéd
	 */
	public void addNeighbour(AsteroidField af){
		neighbours.add(af);
	}

	/**
	 * Felvesz egy új aszteroidát a tárolóba.
	 * @param a új aszteroida
	 */
	public void addAsteroid(Asteroid a) {
		asteroids.add(a);
	}

	/**
	 * Eltávolítunk egy aszteroidát az aszteroidamezőből.
	 * @param a A kitörlendő aszteroida
	 */
	public void removeAsteroid(Iterator<Asteroid> asteroidIter) {
		asteroidIter.remove();
	}

	/**
	 * Beállítja az AsteroidField belt változóját.
	 * @param b az AsteroidFieldek listája
	 */
	public static void setBelt(List<AsteroidField> b) {
		belt = b;
	}

	/**
	 * Visszaadja egy adott aszteroida indexek szerinti koordinátáit.
	 * @param a az aszteroida
	 * @return az indexek szerinti koordináta
	 */
	public Coordinate getIndexes(Asteroid a) {
		int x = belt.indexOf(this);
		int y = asteroids.indexOf(a);
		return new Coordinate(x, y);
	}

	/**
	 * Kiírja a megadott PrintStream-re az általunk definiált config fájloknak megfelelő formátumban az AsteroidField adatait.
	 * @param out ahova kiírja az adatokat
	 */
	public void printToConfig(PrintStream out) {
		for (Asteroid a : asteroids) {
			out.print(',');
			a.printToConfig(out);
		}
	}	
  
	
	/**
	 * Visszaadja az AsteroidField koordinátáit.
	 * @return a koordináta
	 */
	public Coordinate getCoordinates() {
		return position;
	}

	/////////////////////////////////// test
	public AsteroidField(Coordinate c,int t) {
		position = c;
		asteroids = new ArrayList<>();
		neighbours = new ArrayList<>();
		teleportGates = new ArrayList<>();
	}
}
