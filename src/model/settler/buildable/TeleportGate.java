package model.settler.buildable;

import controller.Program;
import model.playfield.AsteroidField;

/**
 * Minden teleportkapu ismeri a párját, rajtuk keresztül oldjuk meg a
 * nem szomszédos hexagonok közti teleportálást.
 */
public class TeleportGate {
	
	/**
	 * A kapu párját tárolja.
	 */
	private TeleportGate otherGate;
	
	/**
	 * A kapu helyszínéül szolgáló aszteroidamezőt tárolja.
	 */
	private AsteroidField asteroidField;
	
	/**
	 * Beállítja a teleportkapu párját.
	 * @param tg A teleportkapu párja.
	 */
	public void setOtherGate(TeleportGate tg) {
		Program.indent++;
		Program.print();
		System.out.println("TeleportGate.setOtherGate()");
		otherGate = tg;
		Program.indent--;
	}
	
	public TeleportGate getOtherGate() {
		Program.indent++;
		Program.print();
		System.out.println("TeleportGate.getOtherGate()");
		Program.indent--;
		return otherGate;
	}
	
	/**
	 * Beállítja a teleportkapu helyét.
	 * @param af Az aszteroidamező, ahova kerül a teleportkapu.
	 */
	public void setAsteroidField(AsteroidField af) {
		Program.indent++;
		Program.print();
		System.out.println("TeleportGate.setAsteroidField()");
		asteroidField = af;
		Program.indent--;
	}
	
	public AsteroidField getAsteroidField() {
		Program.indent++;
		Program.print();
		System.out.println("TeleportGate.getAsteroidField()");
		Program.indent--;
		return asteroidField;
	}
}
