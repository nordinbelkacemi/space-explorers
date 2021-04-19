package model.playfield;

/**
 * Felelős a mező helyzetéért.
 */
public class Hexagon {

	/**
	 * A hexagon pozícióját tárolja.
	 */
	protected Coordinate position;
	
	
	/**
	 * Visszaadja a Hexagon pozícióját.
	 * @return a Hexagon pozíciója
	 */
	public Coordinate getCo() {
		return position;
	}
}
