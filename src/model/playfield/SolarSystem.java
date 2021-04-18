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
		
	}
	
	/**
	 * Meghívja azon AsteroidField-ek ReactToFlare() függvényét amelyeket eltalál a napkitörés.
	 */
	public void reactToFlare(Coordinate d) {
		Coordinate s = sun.getCo();
		for (AsteroidField field : asteroidBelt) {
			Coordinate f = field.getCo();
			
			if(d.getX() == 0) {
				int b = (f.getY()-s.getY())/d.getY();
				if(b > 0) {
					field.reactToFlare();
				}
			}
			else if(d.getX() == 0) {
				int a = (f.getX()-s.getX())/d.getX();
				if(a > 0) {
					field.reactToFlare();
				}
			}
			else {
				int a = (f.getX()-s.getX())/d.getX();
				int b = (f.getY()-s.getY())/d.getY();
				if(a > 0 && a == b) {
					field.reactToFlare();
				}
			}
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
