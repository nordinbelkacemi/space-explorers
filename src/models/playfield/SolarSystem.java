package models.playfield;
import java.util.List;

import java.io.PrintStream;
import java.util.ArrayList;


/**
 * A játéktér maga. A játékelemek közti kommunikációt valósítja meg,
 * a hexagonokat egy bizonyos logika szerint tárolja. Kezdéskor inicializálja a játékteret.
 */
public class SolarSystem {

	/**
	 * A játékteret alkotó koordináták, ezeken a helyeken vannak AsteroidFieldek.
	 */
	private Coordinate[] cos = {
									new Coordinate(-4, 4),new Coordinate(-3, 4),new Coordinate(-2, 4),new Coordinate(-1, 4),new Coordinate(0, 4),
							new Coordinate(-4, 3),new Coordinate(-3, 3),new Coordinate(-2, 3),new Coordinate(-1, 3),new Coordinate(0, 3),new Coordinate(1, 3),
					new Coordinate(-4, 2),new Coordinate(-3, 2),															new Coordinate(1, 2),new Coordinate(2, 2),
			new Coordinate(-4, 1),new Coordinate(-3, 1),																			new Coordinate(2, 1),new Coordinate(3, 1),
	new Coordinate(-4, 0),new Coordinate(-3, 0),																							new Coordinate(3, 0),new Coordinate(4, 0),
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
	
	/**
	 * Konstruktor.
	 * @param s az aszteroidaövben lévő nap
	 */
	public SolarSystem(Sun s) {
		asteroidBelt = createBelt();
		setNeighbours();
		AsteroidField.setBelt(asteroidBelt);
		sun = s;
		sun.setSolarSystem(this);
	}
	
	/**
	 * Létrehozza az aszteroidaövet és inicializálja a benne lévő objektumokat.
	 * @return a játékteret alkotó AsteroidFieldek listája
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
				if(!field1.equals(field2)) {
					if (Math.abs(field1.position.getX() - field2.position.getX()) <= 1 && Math.abs(field1.position.getY() - field2.position.getY()) <= 1) {
						field1.addNeighbour(field2);
					}
				}
			}
		}
	}
	
	/**
	 * Meghívja azon AsteroidField-ek ReactToFlare() függvényét amelyeket eltalál a napkitörés.
	 */
	public void reactToFlare(Coordinate d) {
		Coordinate s = sun.getCo();
		for (AsteroidField field : asteroidBelt) {
			Coordinate f = field.getCo();
			
			if(d.getX() == 0) {
				int b = (f.getY() - s.getY()) / d.getY();
				if (b > 0) {
					field.reactToFlare();
				}
			}
			else if(d.getY() == 0) {
				int a = (f.getX() - s.getX()) / d.getX();
				if (a > 0) {
					field.reactToFlare();
				}
			}
			else {
				int a = (f.getX() - s.getX()) / d.getX();
				int b = (f.getY() - s.getY()) / d.getY();
				if (a > 0 && a == b) {
					field.reactToFlare();
				}
			}
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
	 * Beállítja a napot.
	 * @param s a nap objektum
	 */
	public void setSun(Sun s) {
		sun = s;
	}
	
	/**
	 * Kiírja a megadott PrintStream-re az általunk definiált config fájloknak megfelelő formátumban a SolarSystem adatait.
	 * @param out ahova kiírja az adatokat
	 */
	public void configOut(PrintStream out) {
		int i = 0;
		for (AsteroidField f : asteroidBelt) {
			if(!f.getAsteroids().isEmpty()) {
				out.print(i);
				f.printToConfig(out);
				out.println();
				i++;
			}
		}
		out.println();
		out.println(sun.toString());
	}
	
	/**
	 * Visszaadja a játékteret alkotó AsteroidFieldek listáját.
	 * @return az AsteroidFieldek listája
	 */
	public List<AsteroidField> getBelt(){
		return asteroidBelt;
	}
}
