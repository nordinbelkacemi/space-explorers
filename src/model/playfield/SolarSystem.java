package model.playfield;
import java.util.List;
import java.io.PrintStream;
import java.util.ArrayList;


/**
 * A játéktér maga. A játékelemek közti kommunikációt valósítja meg,
 * a hexagonokat egy bizonyos logika szerint tárolja. Kezdéskor inicializálja a játékteret.
 */
public class SolarSystem {

	private Coordinate[] cos = {
									new Coordinate(-4, 4),new Coordinate(-3, 4),new Coordinate(-2, 4),new Coordinate(-1, 4),new Coordinate(0, 4),
							new Coordinate(-4, 3),new Coordinate(-3, 3),new Coordinate(-2, 3),new Coordinate(-1, 3),new Coordinate(0, 3),new Coordinate(1, 3),
					new Coordinate(-4, 2),new Coordinate(-3, 2),															new Coordinate(1, 2),new Coordinate(2, 2),
			new Coordinate(-4, 1),new Coordinate(-3, 1),																			new Coordinate(2, 1),new Coordinate(3, 1),
	new Coordinate(-4, 0),new Coordinate(-3, 0),																							new Coordinate(3, 1),new Coordinate(4, 0),
			new Coordinate(-3, -1),new Coordinate(-2, -1),																			new Coordinate(3, -1),new Coordinate(4, -1),
					new Coordinate(-2, -2),new Coordinate(-1, -2),															new Coordinate(3, -2),new Coordinate(4, -2),
							new Coordinate(-1, -3),new Coordinate(0, -3),new Coordinate(1, -3),new Coordinate(2, -3),new Coordinate(3, -3),new Coordinate(4, -3),
									new Coordinate(0, -4),new Coordinate(1, -4),new Coordinate(2, -4),new Coordinate(3, -4),new Coordinate(4, -4)
	};
	
	/**
	 * A napot tárolja.
	 */
	private Sun sun;
	
	/**
	 * A játékteret alkotó AsteroidFieldek tárolója.
	 */
	private List<AsteroidField> asteroidBelt;
	
	public SolarSystem(Sun s) {
		asteroidBelt = createBelt();
		setNeighbours();
		AsteroidField.setBelt(asteroidBelt);
		sun = s;
	}
	
	/**
	 * Létrehozza az aszteroidaövet és inicializálja a benne lévő objektumokat.
	 * @return
	 */
	private List<AsteroidField> createBelt() {
		ArrayList<AsteroidField> belt = new ArrayList<>();
		for (int i = 0; i < cos.length; i++) {
			belt.add(new AsteroidField(cos[i]));
		}		
		return belt;
	}
	
	/**
	 * Beállítja az aszteroidamezők szomszédossági viszonyait.
	 */
	private void setNeighbours() {
		for (AsteroidField field1 : asteroidBelt) {
			for (AsteroidField field2 : asteroidBelt) {
				if (Math.abs(field1.position.getX() - field2.position.getX()) <= 1 && Math.abs(field1.position.getY() - field2.position.getY()) <= 1) {
					field1.addNeighbour(field2);
				}
			}
		}
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
		for (AsteroidField field : asteroidBelt) {
			if (Math.abs(field.position.getX() - sun.position.getX()) <= 2 && Math.abs(field.position.getY() - sun.position.getY()) <= 2)
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
	
	public void configOut(PrintStream out) {
		int i = 0;
		for (AsteroidField f : asteroidBelt) {
			out.print(i);
			f.printToConfig(out);
			out.println();
			i++;
		}
	}
	
	public List<AsteroidField> getBelt(){
		return asteroidBelt;
	}
}
