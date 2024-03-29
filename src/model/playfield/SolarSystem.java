package model.playfield;
import java.util.List;

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
	
	/** A megkergült kapukat tartalmazó osztály. */
	public static MegkergultGates megkergultGates = new MegkergultGates();
	
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
			Coordinate co1 = field1.getCo();
			double X1 = co1.getX() + co1.getY() / 2.0f;
			double Y1 = co1.getY() * Math.sqrt(3) / 2.0f;
			for (AsteroidField field2 : asteroidBelt) {
				if(!field1.equals(field2)) {
					Coordinate co2 = field2.getCo();
					double X2 = co2.getX() + co2.getY() / 2.0f;
					double Y2 = co2.getY() * Math.sqrt(3) / 2.0f;
					if (Math.pow(X1 - X2,2) + Math.pow(Y1 - Y2,2) <= 1.1) {
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
			
			if(d.getX() == 0 && s.getX() == f.getX()) {
				int b = (f.getY() - s.getY()) / d.getY();
				if (b > 0) {
					field.reactToFlare();
				}
			}
			else if(d.getY() == 0 && s.getY() == f.getY()) {
				int a = (f.getX() - s.getX()) / d.getX();
				if (a > 0) {
					field.reactToFlare();
				}
			}
			else if(d.getX() != 0 && d.getY() != 0){
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
		Coordinate sunCo = sun.getCo();
		for (AsteroidField field : asteroidBelt) {
			Coordinate co = field.getCo();
			int diffX = sunCo.getX() - co.getX();
			int diffY = sunCo.getY() - co.getY();
			if (Math.abs(diffX) <= 2 && Math.abs(diffY) <= 2 && Math.abs(diffX + diffY) <= 2)
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
	 * Visszaadja a játékteret alkotó AsteroidFieldek listáját.
	 * @return az AsteroidFieldek listája
	 */
	public List<AsteroidField> getBelt(){
		return asteroidBelt;
	}

	public Sun getSun() {
		return sun;
	}
}
