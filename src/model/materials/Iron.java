package model.materials;

import model.settler.Settler;

/** A vas, mint kibányászható nyersanyag osztálya, nem radioaktív, nem szublimál. */
public class Iron extends Material {

    /**
     * Meghívja a paraméterként kapott settler AddIron() függvényét.
     * @param s a kapott Settler
     */
    @Override
    public void store(Settler s) {
        s.addIron(this);
    }
    
    /**
	 * Visszaadja a vas String reprezentációját.
	 * @return a String
	 */
    @Override
    public String toString() {
        return "iron";
    }

}
