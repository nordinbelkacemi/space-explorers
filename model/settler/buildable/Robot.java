package model.settler.buildable;

import controller.Program;
import java.util.ArrayList;
import model.ai.AI;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.settler.Settler;
import model.settler.Traveler;

/**
 * A telepeshez hasonló, de nem játszható karakter, a telepesek tudják megépíteni.
 */
public class Robot extends Traveler implements Buildable {

	/**
	 * A robotot irányító mesterséges intelligencia.
	 */
	private AI ai;
	
	public Robot(Asteroid a) {
		super(a);
		ai = new AI();
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
		Program.indent++;
		Program.print();
		System.out.println("Robot.reactToExplosion()");
		ArrayList<AsteroidField> neighbourFields = getNeighbours();
		ArrayList<Asteroid> availableAsteroids = neighbourFields.get(0).getAsteroids();
		Asteroid a2 = availableAsteroids.get(0);
		this.setPosition(a2);
		Program.indent--;
	}
	
	/**
	 * Beállítja a robot attribútumait a paraméterként kapott, a robotot építő settler
	 * attribútumainak segítségével, valamint hozzáadja a robotot az aszteroida travelers tárolójához.
	 * @param s Az a Settler, aki megépíti az adott eszközt.
	 */
	@Override
	public void build(Settler s) {
		Program.indent++;
		Program.print();
		System.out.println("Robot.build()");
		s.getIron();
		s.getCoal();
		s.getUranium();
		setPosition(asteroid);
		ai.addRobot(this);
		Program.indent--;
	}
}
