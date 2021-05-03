package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.ai.RobotAi;
import models.ai.UfoAi;
import models.playfield.Asteroid;
import models.playfield.AsteroidField;
import models.playfield.MegkergultGates;
import models.playfield.SolarSystem;
import models.playfield.Sun;
import models.settler.Settler;
import models.settler.SettlerTeam;
import views.GameFrame;
import views.SpaceExplorersGui;

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
		for (Settler settler : getSettlers()) {
			selectableSettlers.add(settler);
		}
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
			if (settler.getId() == id)
				selectedSettler = settler;
				break;
		}
		
		SelectedSettler.getInstance().set(selectedSettler);
		gui.settlerSelected();
	}

	/**
	 * Egy AsteroidField-et kiválasztó függvény: beállítja a chosenField-et a
	 * megfelelö asteroid fieldre.
	 * 
	 * @param n A kiválasztott AsteroidField sorszáma
	 */
	public void selectField(int n) {
		/* TODO Game.selectField */
	}

	/**
	 * Egy AsteroidField-et kiválasztó függvény: beállítja a chosenField-et a
	 * megfelelö asteroid fieldre.
	 * 
	 * @param n A kiválasztott AsteroidField sorszáma
	 */
	public void selectAsteroid(int n) {
		/* TODO Game.selectField */
	}

	public void log(String message) {
		/* TODO Game.log */
	}

	public void endTurn() {
		for (Settler settler : getSettlers()) {
			selectableSettlers.add(settler);
		}
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

	// public ArrayList<String> getActions() {
	// 	ArrayList<String> actions = new ArrayList<String>();

	// 	Asteroid currentAsteroid = chosenSettler.getAsteroid();
	// 	ArrayList<AsteroidField> neighbors = currentAsteroid.getNeighbours();

	// 	if (currentAsteroid.getThickness() > 0) {
	// 		actions.add("drill");
	// 	}

	// 	for (AsteroidField af : neighbors) {
	// 		if (af.getAsteroids().size() > 0) {
	// 			actions.add("move");
	// 			break;
	// 		}
	// 	}

	// 	if (currentAsteroid.getThickness() == 0 && !currentAsteroid.isEmpty()) {
	// 		actions.add("mine");
	// 	}

	// 	if (chosenSettler.canBuildGate()) {
	// 		actions.add("build teleportgate");
	// 	}

	// 	if (chosenSettler.canBuildRobot()) {
	// 		actions.add("build robot");
	// 	}

	// 	if (chosenSettler.canPlaceGate()) {
	// 		actions.add("place teleportgate");
	// 	}

	// 	if (currentAsteroid.isEmpty() && currentAsteroid.getThickness() == 0) {
	// 		if (chosenSettler.getIronCount() >= 1) {
	// 			actions.add("putback iron");
	// 		}
	// 		if (chosenSettler.getUraniumCount() >= 1) {
	// 			actions.add("putback uranium");
	// 		}
	// 		if (chosenSettler.getCoalCount() >= 1) {
	// 			actions.add("putback coal");
	// 		}
	// 		if (chosenSettler.getIceCount() >= 1) {
	// 			actions.add("putback ice");
	// 		}
	// 	}

	// 	return actions;
	// }

	// public List<Integer> getChoosableSettlers() {
	// 	return choosableSettlers;
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
