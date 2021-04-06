package model.settler;

import java.util.ArrayList;

import controller.Program;
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
		Program.indent++;
		Program.print();
		System.out.println(this.toString() + ".move()");
		asteroid.removeTraveler(this);
		setPosition(a);
		Program.indent--;
	}
	
	/**
	 * Meghívja az asteroid attribútum által referált aszteroidán a RemoveLayer()-t.
	 */
	public void drill() {
		Program.indent++;
		Program.print();
		System.out.println(this.toString() + ".drill()");
		asteroid.removeLayer();
		Program.indent--;
	}
	
	/**
	 * Az aszteroida felrobbanásának hatására reagál.
	 */
	public void reactToExplosion() {
	}

	/**
	 *  Megöli a telepest vagy robotot, ha az nem bújt el egy üreges aszteroida magjában.
	 */
	public void reactToFlare() {
		Program.indent++;
		Program.print();
		System.out.println(this.toString() + ".reactToFlare");
		die();
		Program.indent--;
	}

	/**
	 * Visszaad egy, a Traveler aszteroidájához tartozó AsteroidFieldet
	 * és annak szomszédait tartalmazó listát.
	 * @return A traveller
	 */
	public ArrayList<AsteroidField> getNeighbours() {
		Program.indent++;
		Program.print();
		System.out.println(this.toString() + ".getNeighbours()");
		ArrayList<AsteroidField> neighbours = asteroid.getNeighbours();
		Program.indent--;
		return neighbours;
	}

	/**
	 * Az asteroid privát taghoz tartozó setter.
	 * @param a
	 */
	public void setPosition(Asteroid a) {
		Program.indent++;
		Program.print();
		System.out.println(this.toString() + ".setPosition()");
		asteroid = a;
		a.addTraveler(this);
		Program.indent--;
	}

	/**
	 * Eltávolítja az utazót mindent tárolójából.
	 */
	protected void die() {
		Program.indent++;
		Program.print();
		System.out.println(this.toString() + ".die()");
		Program.indent--;
	};
}
