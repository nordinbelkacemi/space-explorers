package models.materials;

import models.settler.Settler;

/** A szén, mint kibányászható nyersanyag osztálya, nem radioaktív, nem szublimál. */
public class Coal extends Material {

    /**
     * Meghívja a paraméterként kapott settler AddCoal() függvényét.
     * @param s a kapott Settler
     */
    @Override
    public void store(Settler s) {
    	s.addCoal(this);
    }
    
    /**
	 * Visszaadja a szén String reprezentációját.
	 * @return a String
	 */
    @Override
    public String toString() {
    	return "coal ";
    }
}
