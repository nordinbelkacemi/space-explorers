package model.settler.buildable;


import java.util.ArrayList;
import java.util.List;

import model.ai.RobotAi;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.settler.Driller;
import model.settler.Settler;
import model.settler.Traveler;

/**
 * A telepeshez hasonló, de nem játszható karakter, a telepesek tudják megépíteni.
 */
public class Robot extends Traveler implements Buildable,Driller {

	/**
	 * A robotot irányító mesterséges intelligencia.
	 */
	private RobotAi ai;
	
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
		System.out.println("Robot.reactToExplosion()");
		List<AsteroidField> neighbourFields = getNeighbours();
		List<Asteroid> availableAsteroids = neighbourFields.get(0).getAsteroids();
		Asteroid a2 = availableAsteroids.get(0);
		this.setPosition(a2);
	}
	
	/**
	 * Beállítja a robot attribútumait a paraméterként kapott, a robotot építő settler
	 * attribútumainak segítségével, valamint hozzáadja a robotot az aszteroida travelers tárolójához.
	 * @param s Az a Settler, aki megépíti az adott eszközt.
	 */
	@Override
	public void build(Settler s) {
		System.out.println("Robot.build()");
		s.getIron();
		s.getCoal();
		s.getUranium();
		setPosition(asteroid);
		ai.addRobot(this);
	}

	@Override
	public void drill() {
		
		
	}
}
