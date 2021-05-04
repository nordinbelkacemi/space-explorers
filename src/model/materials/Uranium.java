package model.materials;

import model.playfield.Asteroid;
import model.settler.Settler;

/** Az urán, mint kibányászható nyersanyag osztálya, radioaktív, nem szublimál. */
public class Uranium extends Material {

	/**
	 * Az uránhoz általánosan tartozó tulajdonság.
	 * Megmondja, hogy hányszor érheti napfény az uánt amire felrobban.
	 */
	static private int instability = 3;
	
	/** Egy konkrét urán objektum napfényérintéseinek számát tárolja. */
	private int exposureCount = 0;
	
	/**
	 * Meghívja az aszteroida Explode() függvényét.
	 * @param a az érintett aszteroida
	 */
    @Override
    public void reactToSun(Asteroid a) {
    	a.explode();
    }
    
    public Uranium() {}

	/**Meghívja a paraméterként kapott settler AddUranium() függvényét.
	 * @param s a kapott Settler
	 */
	@Override
	public void store(Settler s) {
		s.addUranium(this);
	}
	
	/**
	 * Visszaadja az urán String reprezentációját.
	 * @return a String
	 */
    @Override
    public String toString() {
        return "uranium " + exposureCount + " ";
    }
    
    ////////////////////////////////// test
    public Uranium(int e) {
		exposureCount = e;
	}
}
