package model.materials;

import console.Console;
import model.playfield.Asteroid;
import model.playfield.Coordinate;
import model.settler.Settler;

/** A vízjég, mint kibányászható nyersanyag osztálya, nem radioaktív, szublimál. */
public class Ice extends Material {
	
    /**
     * Meghívja a paraméterként kapott settler AddIce() függvényét.
     * @param s a kapott Settler
     */
    @Override
    public void store(Settler s) {
    	s.addIce(this);
    }

    /**
     * Meghívja az őt tároló aszteroida Sublime() függvényét.
     * @param a az érintett aszteroida
     */
    @Override
    public void reactToSun(Asteroid a) {
        Coordinate coordinate = a.getIndexes();
        int fieldIdx = coordinate.getX();
        int asteroidIdx = coordinate.getY();

        Console.print(fieldIdx + ". field " + asteroidIdx + ". asteroid sublime");
    	a.sublime();
    }
    
    /**
	 * Visszaadja a jég String reprezentációját.
	 * @return a String
	 */
    @Override
    public String toString() {
        return "ice ";
    }
}
