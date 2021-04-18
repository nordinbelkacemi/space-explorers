package model.settler.buildable;


import java.util.ArrayList;
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

	/**
	 * A robotot irányító mesterséges intelligencia.
	 */
	private RobotAi ai;
	
	private Random r = new Random();
	
	public Robot(Asteroid a) {
		super(a);
		ai = new RobotAi();
	}

	@Override
	public String toString() {
		return "Robot";
	}

	/**
	 * A robotot tartalmazó aszteroida felrobbanása esetén hívódik meg.
	 * Egy véletlenszerű szomszédos aszteroidára helyezi át a robotot.
	 */
	@Override
	public void reactToExplosion() {
		List<AsteroidField> neighbourFields = getNeighbours();
		AsteroidField randomField = neighbourFields.get(r.nextInt(neighbourFields.size()));
		List<Asteroid> availableAsteroids = randomField.getAsteroids();
		Asteroid randomAsteroid = availableAsteroids.get(r.nextInt(availableAsteroids.size()));
		this.setPosition(randomAsteroid);
	}
	
	/**
	 * Beállítja a robot attribútumait a paraméterként kapott, a robotot építő settler
	 * attribútumainak segítségével, valamint hozzáadja a robotot az aszteroida travelers tárolójához.
	 * @param s Az a Settler, aki megépíti az adott eszközt.
	 */
	@Override
	public void build(Settler s) {
		s.getIron();
		s.getCoal();
		s.getUranium();
		this.setPosition(s.getAsteroid());
		ai.addRobot(this);
	}

	@Override
	public void drill() {
		this.asteroid.removeLayer();
	}
}
