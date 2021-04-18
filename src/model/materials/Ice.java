package model.materials;

import model.playfield.Asteroid;
import model.settler.Settler;

/**A vízjég, mint kibányászható nyersanyag osztálya, nem radioaktív, szublimál */
public class Ice extends Material {

    @Override
    public String toString() {
        return "Ice";
    }

    /**Meghívja a paraméterként kapott settler AddIce() függvényét
     * @param s a kapott settler
     */
    @Override
    public void store(Settler s) {
    	
    }

    /**Meghívja az őt tároló aszteroida Sublime() függvényét
     * @param a az érintett aszteroida
     */
    @Override
    public void reactToSun(Asteroid a) {
    }
}
