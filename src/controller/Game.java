package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.ai.RobotAi;
import model.ai.UfoAi;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.playfield.MegkergultGates;
import model.playfield.SolarSystem;
import model.playfield.Sun;
import model.settler.Settler;
import model.settler.SettlerTeam;
import view.GameFrame;
import view.SpaceExplorersGui;

public class Game {
	private static Game instance = new Game();
	private SpaceExplorersGui gui;

	private Sun sun;
	private SolarSystem solarSystem;
	private MegkergultGates megkergultGates;
	private SettlerTeam settlerTeam;
	private RobotAi robotAi;
	private UfoAi ufoAi;

	/**
	 * A kiválasztható/léptethetö telepesek sorszámait tároló lista. (pl. ha van 6
	 * telepes és már léptünk az elsövel, akkor ennek a tartalma: null, 2, 3, 4, 5, 6)
	 */
	private List<Settler> selectableSettlers;

	/** telepes mozgása során kiválasztott cél asteroid field */
	private AsteroidField selectedField;

	/** telepes mozgása során kiválasztott cél aszteroida */
	private Asteroid selectedAsteroid;
	
	private int selectedTeleportgatePair;

	private Game() {
		/* TODO Game.ctor where/how to set gui ? */ // itt csak azokat kell setelni amiket a gui lekérhet

		sun = new Sun();
		solarSystem = new SolarSystem(sun);
		settlerTeam = new SettlerTeam(solarSystem.getBelt());
		robotAi = new RobotAi();
		ufoAi = new UfoAi(solarSystem.getBelt());
		megkergultGates = new MegkergultGates();

		selectableSettlers = new ArrayList<Settler>();
		initSelectableSettlers();
	}

	public static Game getInstance() {
		return instance;
	}

	/**
	 * Egy telepest kiválasztó függvény: beállítja a chosenSettler-t a megfelelo
	 * telepesre
	 * 
	 * @param n A kiválasztott telepes sorszáma
	 */
	public void selectSettler(int id) {
		List<Settler> settlers = settlerTeam.getSettlers();

		Settler selectedSettler = null;
		for (Settler settler : settlers) {
			if (settler.getId() == id) {
				selectedSettler = settler;
				break;
			}
		}
		
		SelectedSettler.getInstance().set(selectedSettler);
		gui.settlerSelected();
	}

	/**
	 * Egy AsteroidField-et kiválasztó függvény: beállítja a selectedField-et a
	 * megfelelö asteroid fieldre.
	 * 
	 * @param n A kiválasztott AsteroidField sorszáma
	 */
	public void selectField(int n) {
		selectedField = solarSystem.getBelt().get(n);
		selectedAsteroid = null;
		gui.fieldSelected();
	}

	/**
	 * Egy AsteroidField-et kiválasztó függvény: beállítja a chosenField-et a
	 * megfelelö asteroid fieldre.
	 * 
	 * @param n A kiválasztott AsteroidField sorszáma
	 */
	public void selectAsteroid(int n) {
		selectedAsteroid = selectedField.getAsteroids().get(n);
		gui.asteroidSelected();
	}

	public void log(String message) {
		/* TODO Game.log */
	}

	public void endTurn() {
		SelectedSettler.getInstance().set(null);
		resetSelectableSettlers();
		selectedField = null;
		selectedAsteroid = null;
		gui.turnEnded();
		
		sun.performAction(); // csak a gui miatt maugy a stepben lesz
	}

	public void checkTurnEnd() {
		boolean turnended = true;
		for (Settler settler : selectableSettlers) {
			if(settler != null) {
				turnended = false;
				break;
			}
		}
		if(turnended)
			endTurn();
	}
	
	
	public void setGui(GameFrame gameFrame) {
		gui = gameFrame;
	}
	
	public AsteroidField getSelectedField() {
		return selectedField;
	}

	public Asteroid getSelectedAsteroid() {
		return selectedAsteroid;
	}

	public SolarSystem getSolarSystem() {
		return solarSystem;
	}

	public List<Settler> getSettlers() {
		return settlerTeam.getSettlers();
	}

	public List<Settler> getSelectableSettlers() {
		return selectableSettlers;
	}

	public void resetSelectableSettlers() {
		selectableSettlers.clear();
		initSelectableSettlers();
	}

	private void initSelectableSettlers() {
		for (Settler settler : getSettlers()) {
			selectableSettlers.add(settler);
		}
	}














	// /** A játékot elindító függvény */
	// public void start() {
	// 
	// }

	// public void resetChoosableSettlers() {
	// 	choosableSettlers.clear();
	// 	for (int i = 1; i <= settlerTeam.getSettlers().size(); i++) {
	// 		choosableSettlers.add(i);
	// 	}
	// }
	
	// /**
	//  * A gameOver változó lekérdezö függvénye
	//  * 
	//  * @return True ha a játék véget ért, egyébként False
	//  */
	// public boolean over() {
	// 	return getChoosableSettlers().isEmpty();
	// }

	// public void endSettlerTurn(int n) {
	// 	choosableSettlers.set(n - 1, null);
	// }

	// public ArrayList<AsteroidField> getNeighbours() {
	// 	return chosenSettler.getNeighbours();
	// }

	// public AsteroidField getField(int idx) {
	// 	return chosenSettler.getAsteroid().getNeighbours().get(idx);
	// }

	// public List<Asteroid> getFieldAsteroids(int idx) {
	// 	AsteroidField field = getField(idx);
	// 	List<Asteroid> onlyNeighbours = new ArrayList<Asteroid>();
	// 	for (Asteroid asteroid : field.getAsteroids()) {
	// 		if (asteroid != chosenSettler.getAsteroid()) {
	// 			onlyNeighbours.add(asteroid);
	// 		}
	// 	}
	// 	return onlyNeighbours;
	// }


	// public AsteroidField getSelectedField() {
	// 	return selectedField;
	// }
	
	// public int getNumberofTeleportgatePairs() {
	// 	return chosenSettler.getNumberofTeleportgatePairs();
	// }
	
	// public void selectTeleportgatePair(int selectedPair) {
	// 	selectedTeleportgatePair = selectedPair;
	// }

	// public void step() {
	// 	sun.performAction();
	// 	robotAi.control();
	// 	ufoAi.control();

	// 	resetChoosableSettlers();
	// }
}
