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
	 * 
	 * @return 1 ha történt napkitörés, 0 ha nem.
	 */
	public void performAction() {
		if (r.nextInt(5) == 1) {
			startFlare();
		}
		move();
	}
	
	/**
	 * Elindítja a napvihart.
	 */
	private void startFlare() {
		Coordinate dir;
		switch (r.nextInt(6)) {
			case 0: dir = new Coordinate(0, 1);		break;
			case 1: dir = new Coordinate(1, 0);		break;
			case 2: dir = new Coordinate(1, -1);	break;
			case 3: dir = new Coordinate(0, -1);	break;
			case 4: dir = new Coordinate(-1, 0);	break;
			case 5: dir = new Coordinate(-1, 1);	break;
			default: dir = null;
		}
		solarSystem.reactToFlare(dir);
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
	
	/////////////////////////////// test
	
	public Sun(String s, SolarSystem solarSystem) {
		String[] data = s.split(" ");
		position = new Coordinate(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
		solarSystem.setSun(this);
	}
	
	@Override
	public String toString() {
		return position.toString();
	}
	
}
