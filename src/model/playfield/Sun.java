package model.playfield;


/**
 * Tőle függ, hogy melyik aszteroidamezők vannak napközelben,
 * valamint a napkitöréseket is ez az osztály indítja el.
 */
public class Sun extends Hexagon {

	/**
	 * A naprendszert tárolja.
	 */
	private SolarSystem solarSystem;
	
	/**
	 * Kezeli a Nap mozgását és a napkitöréseket.
	 */
	public void performAction() {
		// startFlare();
		// move();
	}
	
	/**
	 * Elindítja a napvihart.
	 */
	private void startFlare() {
		solarSystem.reactToFlare();
	}
	
	/**
	 * Elmozdítja a napot a következő pozíciójába.
	 */
	private void move() {
		solarSystem.updateDangerZone();
	}
	
	/**
	 * SolarSystem setter.
	 * @param ss
	 */
	public void setSolarSystem(SolarSystem ss) {
		solarSystem = ss;
	}
}
