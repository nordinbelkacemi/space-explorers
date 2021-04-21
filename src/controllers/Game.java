package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import console.exceptions.InvalidCmdException;
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

	// private Console console;

	public Game() {
		// playfield
		sun = new Sun();
		solarSystem = new SolarSystem(sun);

		// settlers
		settlerTeam = new SettlerTeam(solarSystem.getBelt());
		// robot
		robotAi = new RobotAi();
		// ufo
		ufoAi = new UfoAi(solarSystem.getBelt());
		// megkergült
		megkergultGates = new MegkergultGates();

		choosableSettlers = new ArrayList<Integer>();
		//configOut(System.out);
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
	public void chooseSettler(int n) throws InvalidCmdException {
		if (!choosableSettlers.contains(n)) {
			throw new InvalidCmdException();
		}
		chosenSettler = settlerTeam.chooseSettler(n);
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

	public void selectField(int idx) throws InvalidCmdException {
		List<AsteroidField> neighbors = getNeighbours();
		if (idx >= 0 && idx < neighbors.size()) {
			selectedField = getField(idx);
		} else {
			throw new InvalidCmdException();
		}
	}

	public void selectAsteroid(int n) throws InvalidCmdException {
		List<Asteroid> asteroids = selectedField.getAsteroids();
		if (n >= 1 && n <= asteroids.size()) {
			selectedAsteroid = selectedField.getAsteroids().get(n - 1);
		} else {
			throw new InvalidCmdException();
		}
	}
	

	public AsteroidField getSelectedField() {
		return selectedField;
	}
	
	public int getNumberofTeleportgatePairs() {
		return chosenSettler.getNumberofTeleportgatePairs();
	}
	
	public void selectTeleportgatePair(int selectedPair) throws InvalidCmdException {
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

	public String step() {
		String output = new String("");
		output += sun.performAction();
		robotAi.control();
		ufoAi.control();

		resetChoosableSettlers();

		return output;
	}
	
	
	/////////////////////////////////////// test
	
	public Game(Scanner sc) throws InvalidCmdException, FileNotFoundException {
		solarSystem = new SolarSystem();
		settlerTeam = new SettlerTeam(solarSystem.getBelt(),0);
		robotAi = new RobotAi();
		ufoAi = new UfoAi(solarSystem.getBelt(),0);
		megkergultGates = new MegkergultGates();

		choosableSettlers = new ArrayList<Integer>();
		configIn(sc);
	}

	public void configOut(PrintStream out) {
		solarSystem.configOut(out);
		out.println();
		settlerTeam.configOut(out);
		out.println();
		robotAi.configOut(out);
		out.println();
		ufoAi.configOut(out);
	}

	public void configIn(Scanner scanner) throws InvalidCmdException, FileNotFoundException  {
		int state = 0; // 0-asteroids 1-sun 2-settlers 3-robots 4-ufos 5-gates
		String line = null;
		Scanner sc;
		if (scanner.hasNextLine()) {
			sc = new Scanner(new File("src/test/" + scanner.nextLine()));
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				if (line.isEmpty()) {
					state++;
					continue;
				}
				switch (state) {
				case 0:
					solarSystem.createField(line);
					break;
				case 1:
					sun = new Sun(line, solarSystem);
					sun.setSolarSystem(solarSystem);
					break;
				case 2:
					settlerTeam.addSettler(line);
					break;
				case 3:
					robotAi.addRobot(line,solarSystem.getBelt());
					break;
				case 4:
					ufoAi.addUfo(line, solarSystem.getBelt());
					break;
				case 5:
					new TeleportGatePair(line,settlerTeam,solarSystem.getBelt(),megkergultGates);
					break;
				default:
					break;
				}
			}
		}
	}
}
