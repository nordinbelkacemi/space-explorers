package model.playfield;
import java.util.List;
import java.util.ArrayList;

import controller.Program;

/**
 * A játéktér maga. A játékelemek közti kommunikációt valósítja meg,
 * a hexagonokat egy bizonyos logika szerint tárolja. Kezdéskor inicializálja a játékteret.
 */
public class SolarSystem {

	/**
	 * A napot tárolja.
	 */
	private Sun sun;
	
	/**
	 * A játékteret alkotó AsteroidFieldek tárolója.
	 */
	private List<AsteroidField> asteroidBelt;
	
	public SolarSystem() {
		asteroidBelt = new ArrayList<>();
	}
	
	/**
	 * Létrehozza az aszteroidaövet és inicializálja a benne lévő objektumokat.
	 * @return
	 */
	private List<AsteroidField> createBelt() {
		return null;
	}
	
	/**
	 * Beállítja az aszteroidamezők szomszédossági viszonyait.
	 */
	private void setNeighbours() {
		
	}
	
	/**
	 * Meghívja az összes AsteroidField ReactToFlare() függvényét.
	 */
	public void reactToFlare() {
		// nem feltétlenül jó
		for (AsteroidField field : asteroidBelt) {
			field.reactToFlare();
		}

	}

	
	/**
	 * A napközelben lévő AsteroidField-ekre meghívja a CheckDangers() függvényt.
	 */
	public void updateDangerZone() {
		// nem feltétlenül jó 
		for (AsteroidField field : asteroidBelt) {
			field.checkDangers();
		}

	}
	
	/**
	 * Felvesz egy új aszteroidamezőt a tárolóba.
	 * @param af új aszteroidamező
	 */
	public void addField(AsteroidField af) {
		asteroidBelt.add(af);
	}
	
	/**
	 * Sun setter.
	 * @param s 
	 */
	public void setSun(Sun s) {
		sun = s;
	}
}
