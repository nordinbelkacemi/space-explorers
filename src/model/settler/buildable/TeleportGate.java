package model.settler.buildable;

import java.util.List;
import java.util.Random;

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
	 * A megkergült kapu mozgatásakor használt Random objektum.
	 */
	Random r = new Random();
	
	/**
	 * A kapu helyszínéül szolgáló aszteroidamezőt tárolja.
	 */
	private AsteroidField asteroidField;
	
	/** Áthelyezi a kaput egy véletlen választott szomszádos aszteroida mezőre. */
	public void kergul() {
		List<AsteroidField> neighbourFields = asteroidField.getNeighbours();
		AsteroidField randomField = neighbourFields.get(r.nextInt(neighbourFields.size()));
		setAsteroidField(randomField);
		randomField.addTeleportGate(this);
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
