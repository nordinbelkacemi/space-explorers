package model.settler.buildable;


import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import model.ai.RobotAi;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.settler.Driller;
import model.settler.Settler;
import model.settler.Traveler;

/**
 * A telepeshez hasonló, de nem játszható karakter, a telepesek tudják megépíteni.
 */
public class Robot extends Traveler implements Buildable, Driller {

	private static int idCount = 0;
	
	/**
	 * A robotot irányító mesterséges intelligencia.
	 */
	private static RobotAi ai;
	
	/**
	 * A robot aszteroidájának felrobbanásakor használt Random objektum.
	 */
	private Random r = new Random();
	
	/**
	 * Konstruktor.
	 * @param a az aszteroida, amelyiken megépül a robot
	 */
	public Robot() {
		id = idCount++;
	}
	
	/**
	 * Beállítja a robot ai változóját .
	 * @param rai a beállítandó RobotAi objektum
	 */
	public static void setAi(RobotAi rai) {
		ai = rai;
	}

	/**
	 * Visszaadja a robot String reprezentációját.
	 * @return a String
	 */
	@Override
	public String toString() {
		return "robot";
	}

	/**
	 * A robotot tartalmazó aszteroida felrobbanása esetén hívódik meg.
	 * Egy véletlenszerű szomszédos aszteroidára helyezi át a robotot.
	 */
	@Override
	public void reactToExplosion(Iterator<Traveler> travelerIter) {
		List<AsteroidField> neighbourFields = getNeighbours();
		AsteroidField randomField = neighbourFields.get(r.nextInt(neighbourFields.size()));
		List<Asteroid> availableAsteroids = randomField.getAsteroids();
		Asteroid randomAsteroid = availableAsteroids.get(r.nextInt(availableAsteroids.size()));
		this.setPosition(randomAsteroid);
	}
	
	@Override
	public void die() {
		ai.remove(this);
		super.die();
	}
	
	/**
	 * Beállítja a robot attribútumait a paraméterként kapott, a robotot építő settler
	 * attribútumainak segítségével, valamint hozzáadja a robotot az aszteroida travelers tárolójához.
	 * @param s az a Settler, aki megépíti az adott eszközt
	 */
	@Override
	public void build(Settler s) {
		s.getIron();
		s.getCoal();
		s.getUranium();
		this.setPosition(s.getAsteroid());
		ai.addRobot(this);
	}

	/**
	 * A fúrást megvalósító metódus.
	 */
	@Override
	public void drill() {
		this.asteroid.removeLayer();
	}
	
	/**
	 * Kiírja a megadott PrintStream-re az általunk definiált config fájloknak
	 * megfelelő formátumban a Robot adatait.
	 * 
	 * @param out ahova kiírja az adatokat
	 */
	public void printToConfig(PrintStream out) {
		out.print(asteroid.getIndexes().toString());
	}
	
	/**
	 * Napvihar hatására elpusztítja a robotot.
	 */
	@Override
	public void reactToFlare(Iterator<Traveler> travelerIter) {
		super.reactToFlare(travelerIter);
		ai.remove(travelerIter);
		// ai.remove(this);
	}
}
