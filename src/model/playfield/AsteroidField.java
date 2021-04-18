package model.playfield;

import java.io.PrintStream;
import java.util.ArrayList;
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
public class AsteroidField extends Hexagon{
	
	///////////////////////////////////////// atribuuuts
	
	Random r  = new Random();
	
	/** Az AsteroidFieldhez tartozó aszteroidákat tárolja. */
	private List<Asteroid> asteroids;

	/** A szomszédos AsteroidFieldeket tartalmazza. */
	private List<AsteroidField> neighbours;

	/** Az AsteroidFielden elhelyezkedő teleportkapukat tárolja. */
	private List<TeleportGate> teleportGates;

	/**A megkergült kapukat tartalmazó osztály. */
	private static MegkergultGates megkergultGates;

	//////////////////////////////////////// ctors
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

	private List<Asteroid> createAsteroids() {
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
		for (Asteroid asteroid : asteroids) {
			asteroid.reactToFlare();
		}
	}

	/**
	 * Napközeli AsteroidFieldekre hívódik meg,
	 * és minden aszteroidájára meghívja a CheckDangers() függvényt.
	 */
	public void checkDangers() {
		for (Asteroid asteroid : asteroids) {
			asteroid.checkDangers();
		}
	}

	/**
	 * Visszaad egy, az aszteroidához tartozó AsteroidFieldet és annak szomszédait tartalmazó listát.
	 * @return a szomszédos aszteroidákat tartalmazó aszteroidamezők
	 */
	public List<AsteroidField> getNeighbours() {
		ArrayList<AsteroidField> neighbourFields= new ArrayList<AsteroidField>();
		neighbourFields.add(this);
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
	public void AddAsteroid(Asteroid a) {
		asteroids.add(a);
	}
	
	public void printToConfig(PrintStream out) {
		for (Asteroid a : asteroids) {
			out.print(',');
			a.printToConfig(out);
		}
	}
}
