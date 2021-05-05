package model.settler;

import java.util.ArrayList;
import java.util.Iterator;

import controller.Game;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;

/**
 * Absztrakt ősosztály.
 * Az aszteroidák között mozgó entitásokat (telepesek és robotok) reprezentálja,
 * számontartja az aszteroidát, amin tartózkodik.
 */
public abstract class Traveler {

	/**
	 * Az aszteroida, amelyen a Traveler jelenleg tartózkodik.
	 */
	protected Asteroid asteroid;

	protected Iterator<Traveler> travelerIter;
	
	/**
	 * Paraméter nélküli konstruktor.
	 */
	public Traveler() { }

	/**
	 * Konstruktor.
	 * @param a az aszteroida, amelyiken tartózkodik a telepes
	 */
	public Traveler(Asteroid a) {
		setPosition(a);
	}
	
	/**
	 * Visszaadja a Traveler aszteroidáját.
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
	 * @param a az aszteroida amire átrepul a telepes
	 */
	public void move(Asteroid a) {
		asteroid.removeTraveler(this);
		setPosition(a);
	}
	
	/**
	 * Az aszteroida felrobbanásának hatására reagál.
	 */
	public void reactToExplosion(Iterator<Traveler> travelerIter) {
		this.travelerIter = travelerIter;
		die();
	}

	/**
	 *  Megöli a telepest vagy robotot, ha az nem bújt el egy üreges aszteroida magjában.
	 */
	public void reactToFlare(Iterator<Traveler> travelerIter) {
		this.travelerIter = travelerIter;
		Game.getInstance().log(toString() + " died");
		die();
	}

	/**
	 * Visszaad egy, a Traveler aszteroidájához tartozó AsteroidFieldet
	 * és annak szomszédait tartalmazó listát.
	 * @return a Traveler
	 */
	public ArrayList<AsteroidField> getNeighbours() {
		ArrayList<AsteroidField> neighbours = asteroid.getNeighbours();
		return neighbours;
	}

	/**
	 * Beállítja a Traveler új pozícióját.
	 * @param a a Traveler új pozíciója
	 */
	public void setPosition(Asteroid a) {
		asteroid = a;
		a.addTraveler(this);
	}

	/**
	 * Eltávolítja az utazót mindent tárolójából.
	 */
	protected void die() {
		asteroid.removeTraveler(travelerIter);
	}

	protected String getTravelerInfo() {
		return new String("");
	}
}
