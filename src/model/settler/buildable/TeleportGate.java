package model.settler.buildable;

import controller.Game;
import model.playfield.AsteroidField;
import model.playfield.Coordinate;
import model.playfield.SolarSystem;

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
	 * A kapu kergültségét tároló logikai érték.
	 */
	private boolean kergult = false;
	
	/** Megkergíti a teleportkaput. */
	public void kergul() {
		if (!kergult) {
			SolarSystem.megkergultGates.addGate(this);
			Coordinate i = asteroidField.getAsteroids().get(0).getIndexes();
			Game.getInstance().log("Teleportgate at field " + i.getX() + " megkergült.");
			kergult = true;
		}
	}
	
	/**
	 * Beállítja a teleportkapu párját.
	 * @param tg a teleportkapu párja
	 */
	public void setOtherGate(TeleportGate tg) {
		otherGate = tg;
	}
	
	/**
	 * Visszaadja a teleportkapu párját.
	 * @return a teleportkapu párja
	 */
	public TeleportGate getOtherGate() {
		return otherGate;
	}
	
	/**
	 * Beállítja a teleportkapu helyét.
	 * @param af az aszteroidamező, ahova kerül a teleportkapu
	 */
	public void setAsteroidField(AsteroidField af) {
		asteroidField = af;
	}
	
	/**
	 * Visszaadja a teleportkapu helyét.
	 * @return a teleportkapu helye
	 */
	public AsteroidField getAsteroidField() {
		return asteroidField;
	}

}
