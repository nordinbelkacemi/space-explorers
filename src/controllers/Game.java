package controllers;

import java.util.ArrayList;
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
import model.settler.buildable.Robot;
import model.settler.buildable.TeleportGatePair;



public class Game {
	private Sun sun;
	private SolarSystem solarSystem;
	private MegkergultGates megkergultGates;
	private SettlerTeam settlerTeam;
	private RobotAi robotAi;
	private UfoAi ufoAi;

	/** A kiválasztott telepes. Ezzel a telepessel léphet a felhasználó (move, drill stb) */
	private Settler chosenSettler;

	/** A kiválasztható/léptethetö telepesek sorszámait tároló lista. (pl. ha van 6 telepes és már léptünk az elsövel, akkor ennek a tartalma: null, 2, 3, 4, 5, 6) */
	private List<Integer> choosableSettlers;

	/** telepes mozgása során kiválasztott cél asteroid field */
	private AsteroidField selectedField;

	/** telepes mozgása során kiválasztott cél aszteroida */
	private Asteroid selectedAsteroid;
	
	private int selectedTeleportgatePair;

	public Game() {
		sun = new Sun();
		solarSystem = new SolarSystem(sun);
		settlerTeam = new SettlerTeam(solarSystem.getBelt());
		robotAi = new RobotAi();
		ufoAi = new UfoAi(solarSystem.getBelt());
		megkergultGates = new MegkergultGates();

		choosableSettlers = new ArrayList<Integer>();
	}

	/** A játékot elindító függvény */
	public void start() {
		resetChoosableSettlers();
	}

	public void resetChoosableSettlers() {
		choosableSettlers.clear();
		for (int i = 1; i <= settlerTeam.getSettlers().size(); i++) {
			choosableSettlers.add(i);
		}
	}

	/**
	 * Egy telepest kiválasztó függvény: beállítja a chosenSettler-t a megfelelo telepesre
	 * @param n A kiválasztott telepes sorszáma
	 */
	public void chooseSettler(int n) {

	}

	/** A gameOver változó lekérdezö függvénye
	 * @return True ha a játék véget ért, egyébként False
	 */
	public boolean over() {
		return getChoosableSettlers().isEmpty();
	}

	public ArrayList<String> getActions() {
		ArrayList<String> actions = new ArrayList<String>();

		Asteroid currentAsteroid = chosenSettler.getAsteroid();
		ArrayList<AsteroidField> neighbors = currentAsteroid.getNeighbours();

		if (currentAsteroid.getThickness() > 0) {
			actions.add("drill");
		}

		for (AsteroidField af : neighbors) {
			if (af.getAsteroids().size() > 0) {
				actions.add("move");
				break;
			}
		}

		if (currentAsteroid.getThickness() == 0 && !currentAsteroid.isEmpty()) {
			actions.add("mine");
		}

		if (chosenSettler.canBuildGate()) {
			actions.add("build teleportgate");
		}

		if (chosenSettler.canBuildRobot()) {
			actions.add("build robot");
		}

		if (chosenSettler.canPlaceGate()) {
			actions.add("place teleportgate");
		}

		if (currentAsteroid.isEmpty() && currentAsteroid.getThickness() == 0) {
			if (chosenSettler.getIronCount() >= 1) {
				actions.add("putback iron");
			}
			if (chosenSettler.getUraniumCount() >= 1) {
				actions.add("putback uranium");
			}
			if (chosenSettler.getCoalCount() >= 1) {
				actions.add("putback coal");
			}
			if (chosenSettler.getIceCount() >= 1) {
				actions.add("putback ice");
			}
		}

		return actions;
	}

	public List<Integer> getChoosableSettlers() {
		return choosableSettlers;
	}

	public void endSettlerTurn(int n) {
		choosableSettlers.set(n - 1, null);
	}

	public ArrayList<AsteroidField> getNeighbours() {
		return chosenSettler.getNeighbours();
	}

	public void mine() {
		chosenSettler.mine();
	}

	public void drill() {
		chosenSettler.drill();
	}

	public void buildTeleportGate() {
		chosenSettler.build(new TeleportGatePair());
	}

	public void placeTeleportGate() {
		chosenSettler.placeTeleportGate(selectedTeleportgatePair);
	}

	public void buildRobot() {
		Robot r = new Robot(chosenSettler.getAsteroid());
		chosenSettler.build(r);
	}

	public AsteroidField getField(int idx) {
		return chosenSettler.getAsteroid().getNeighbours().get(idx);
	}

	public List<Asteroid> getFieldAsteroids(int idx) {
		AsteroidField field = getField(idx);
		List<Asteroid> onlyNeighbours = new ArrayList<Asteroid>();
		for (Asteroid asteroid : field.getAsteroids()) {
			if (asteroid != chosenSettler.getAsteroid()) {
				onlyNeighbours.add(asteroid);
			}
		}
		return onlyNeighbours;
	}

	public void chooseField(int idx) {
		
	}

	public void chooseAsteroid(int n) {
		
	}

	public AsteroidField getSelectedField() {
		return selectedField;
	}
	
	public int getNumberofTeleportgatePairs() {
		return chosenSettler.getNumberofTeleportgatePairs();
	}
	
	public void selectTeleportgatePair(int selectedPair) {
		selectedTeleportgatePair = selectedPair;
	}

	public void moveSettler() {
		chosenSettler.move(selectedAsteroid);
	}

	public void putIronBack() {
		chosenSettler.putIronBack();
	}

	public void putCoalBack() {
		chosenSettler.putCoalBack();
	}

	public void putUraniumBack() {
		chosenSettler.putUraniumBack();
	}

	public void putIceBack() {
		chosenSettler.putIceBack();
	}

	public void step() {
		sun.performAction();
		robotAi.control();
		ufoAi.control();

		resetChoosableSettlers();
	}

}
