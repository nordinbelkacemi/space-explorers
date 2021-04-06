package model.materials;

import controller.Program;
import model.playfield.Asteroid;
import model.settler.Settler;

/**Az urán, mint kibányászható nyersanyag osztálya, radioaktív, nem szublimál */
public class Uranium extends Material {

	/**Meghívja az aszteroida Explode() függvényét
	 * @param a az érintett aszteroida
	 */
    @Override
    public void reactToSun(Asteroid a) {
    	Program.indent++;
		Program.print();
    	System.out.println("Uranium.reactToSun()");
    	a.explode();
    	Program.indent--;
    }

	/**Meghívja a paraméterként kapott settler AddUranium() függvényét
	 * @param s a kapott settler
	 */
	@Override
	public void store(Settler s) {}
}
