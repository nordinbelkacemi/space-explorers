package model.materials;

import model.settler.Settler;

/**A vas, mint kibányászható nyersanyag osztálya, nem radioaktív, nem szublimál */
public class Iron extends Material {

    /**Meghívja a paraméterként kapott settler AddIron() függvényét
     * @param s a kapott settler
     */
    @Override
    public void store(Settler s) {
        s.addIron(this);
    }
    
    @Override
    public String toString() {
        return "iron ";
    }

}