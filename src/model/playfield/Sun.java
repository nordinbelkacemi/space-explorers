package model.playfield;

import java.util.Random;

/**
 * Tőle függ, hogy melyik aszteroidamezők vannak napközelben,
 * valamint a napkitöréseket is ez az osztály indítja el.
 */
public class Sun extends Hexagon {

	/**
	 * A naprendszert tárolja.
	 */
	private SolarSystem solarSystem;
	
	private Random r = new Random();
	
	private int coordCounter = 0;
	
	public Sun() {
		position = new Coordinate(2, 0);
	}
	
	/**
	 * Kezeli a Nap mozgását és a napkitöréseket.
	 */
	public void performAction() {
		if (r.nextInt(5) == 1)
			startFlare();
		move();
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
		switch ((coordCounter/2) % 6) {
			case 0: position.setX(position.getX() - 1); position.setY(position.getY() + 1);	break;
			case 1: position.setX(position.getX() - 1); position.setY(position.getY());		break;
			case 2: position.setX(position.getX()); 	position.setY(position.getY() - 1);	break;
			case 3: position.setX(position.getX() + 1); position.setY(position.getY() - 1);	break;
			case 4: position.setX(position.getX() + 1); position.setY(position.getY());		break;
			case 5: position.setX(position.getX()); 	position.setY(position.getY() + 1);	break;
		}
		coordCounter++;
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
