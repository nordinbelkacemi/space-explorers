package model.materials;

import model.settler.Settler;

/**A szén, mint kibányászható nyersanyag osztálya, nem radioaktív, nem szublimál */
public class Coal extends Material {

    /**Meghívja a paraméterként kapott settler AddCoal() függvényét
     * @param s a kapott settler
     */
    @Override
    public void store(Settler s) {
    	s.addCoal(this);
    }
    
    @Override
    public String toString() {
    	return "coal ";
    }
}
