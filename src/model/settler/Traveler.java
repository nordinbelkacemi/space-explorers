package model.settler;

import java.util.ArrayList;
import java.util.List;

import model.playfield.Asteroid;
import model.playfield.AsteroidField;

/**
 * Absztrakt ősosztály.
 * Az aszteroidák között mozgó entitásokat (telepesek és robotok) reprezentálja,
 * számontartja az aszteroidát, amin tartózkodik.
 */
public class Traveler {

	/**
	 * Az aszteroida, amelyen a Traveler jelenleg tartózkodik.
	 */
	protected Asteroid asteroid;
	
	public Traveler() { }

	public Traveler(Asteroid a) {
		asteroid = a;
	}
	
	/**
	 * Visszaadja a Traveler aszteroidáját
	 * @return a Traveler aszteroidája
	 */
	public Asteroid getAsteroid() {
		return asteroid;
	}

	/** 
	 * A Traveler egy szomszédos aszteroidára való átmozgását oldja meg; 
	 * meghívja a RemoveTraveler() függvényt arra az aszteroidára, amelyen a Traveler jelenleg tartózkodik, 
	 * a SetPosition() függvénnyel pedig átállítja az asteroid attribútumot a 
	 * paraméterként kapott aszteroidára.
	 * @param a Az aszteroida amire átrepul a telepes.
	 */
	public void move(Asteroid a) {
		asteroid.removeTraveler(this);
		setPosition(a);
	}
	
	/**
	 * Az aszteroida felrobbanásának hatására reagál.
	 */
	public void reactToExplosion() {
		die();
	}

	/**
	 *  Megöli a telepest vagy robotot, ha az nem bújt el egy üreges aszteroida magjában.
	 */
	public void reactToFlare() {
		die();
	}

	/**
	 * Visszaad egy, a Traveler aszteroidájához tartozó AsteroidFieldet
	 * és annak szomszédait tartalmazó listát.
	 * @return A traveller
	 */
	public ArrayList<AsteroidField> getNeighbours() {
		ArrayList<AsteroidField> neighbours = asteroid.getNeighbours();
		return neighbours;
	}

	/**
	 * Az asteroid privát taghoz tartozó setter.
	 * @param a
	 */
	public void setPosition(Asteroid a) {
		asteroid = a;
		a.addTraveler(this);
	}

	/**
	 * Eltávolítja az utazót mindent tárolójából.
	 */
	protected void die() {
		asteroid.removeTraveler(this);
	}
}
