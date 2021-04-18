package model.settler.buildable;

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
	
	/** Áthelyezi a kaput egy véletlen választott szomszádos aszteroida mezőre. */
	public void kergul() {
		
	}
	
	/**
	 * Beállítja a teleportkapu párját.
	 * @param tg A teleportkapu párja.
	 */
	public void setOtherGate(TeleportGate tg) {
		otherGate = tg;
	}
	
	public TeleportGate getOtherGate() {
		return otherGate;
	}
	
	/**
	 * Beállítja a teleportkapu helyét.
	 * @param af Az aszteroidamező, ahova kerül a teleportkapu.
	 */
	public void setAsteroidField(AsteroidField af) {

		asteroidField = af;

	}
	
	public AsteroidField getAsteroidField() {

		return asteroidField;
	}

}
