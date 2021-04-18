package model.ai;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.playfield.AsteroidField;
import model.playfield.Ufo;
import model.settler.Settler;

/**
 * Az ufok tárolásáért és irányításáért felelős objektum.
 */
public class UfoAi implements Ai {

	/**
	 * A settlerek által épített és AI által vezérelt robotokat tartalmazza.
	 */
	private List<Ufo> ufos;
	
	public UfoAi(List<AsteroidField> b) {
		Ufo.setAi(this);
		Random r  = new Random();
		ufos = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			ufos.add(new Ufo(b.get(r.nextInt(42)).getAsteroids().get(0)));
		}
	}
	
	public void configOut(PrintStream out) {
		for (Ufo u : ufos) {
			u.printToConfig(out);
			out.println();
		}
	}
	
	/**
	* Meghatározza az ufok következő lépését és végre is hajtja azokat.
	*/
	public void control() {
		
	}
}
