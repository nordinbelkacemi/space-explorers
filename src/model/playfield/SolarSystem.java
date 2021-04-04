package model.playfield;

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
	private ArrayList<AsteroidField> asteroidBelt;
	
	public SolarSystem() {
		asteroidBelt = new ArrayList<>();
	}
	
	/**
	 * Létrehozza az aszteroidaövet és inicializálja a benne lévő objektumokat.
	 * @return
	 */
	private ArrayList<AsteroidField> createBelt() {
		return null;
	}
	
	/**
	 * Meghívja az összes AsteroidField ReactToFlare() függvényét.
	 */
	public void reactToFlare() {
		Program.indent++;
		Program.print();
		System.out.println("Sun.startFlare");
		for (AsteroidField field : asteroidBelt) {
			field.reactToFlare();
		}
		Program.indent--;
	}
	
	/**
	 * Beállítja az aszteroidamezők szomszédossági viszonyait.
	 */
	private void setNeighbours() {
		
	}
	
	/**
	 * A napközelben lévő AsteroidField-ekre meghívja a CheckDangers() függvényt.
	 */
	public void updateDangerZone() {
		Program.indent++;
		Program.print();
		System.out.println("SolarSystem.updateDangerZone()");
		for (AsteroidField field : asteroidBelt) {
			field.checkDangers();
		}
		Program.indent--;
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
