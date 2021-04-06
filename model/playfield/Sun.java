package model.playfield;

import controller.Program;

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
		
	}
	
	/**
	 * Elindítja a napvihart.
	 */
	public void startFlare() {
		Program.indent++;
		Program.print();
		System.out.println("Sun.startFlare");
		solarSystem.reactToFlare();
		Program.indent--;
	}
	
	/**
	 * Elmozdítja a napot a következő pozíciójába.
	 */
	public void move() {
		Program.indent++;
		Program.print();
		System.out.println("Sun.move()");
		solarSystem.updateDangerZone();
		Program.indent--;
	}
	
	/**
	 * SolarSystem setter.
	 * @param ss
	 */
	public void setSolarSystem(SolarSystem ss) {
		solarSystem = ss;
	}
}
