package controllers;

import java.io.InputStream;
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

	/** A játék végét jelzö valtozó: Ha vége a játék, akkor az értéke true, különben false */
	private boolean gameOver;

	/** A kiválasztott telepes. Ezzel a telepessel léphet a felhasználó (move, drill stb) */
	private Settler chosenSettler;

	/** A kiválasztható/léptethetö telepesek sorszámait tároló lista. (pl. ha van 6 telepes és már léptünk az elsövel, akkor ennek a tartalma: null, 2, 3, 4, 5, 6) */
	private List<Integer> choosableSettlers;

	/** telepes mozgása során kiválasztott cél asteroid field */
	private AsteroidField selectedField;

	/** telepes mozgása során kiválasztott cél aszteroida */
	private Asteroid selectedAsteroid;

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
		gameOver = false;
	}

	public void resetChoosableSettlers() {
		choosableSettlers.clear();
		for (int i = 1; i <= 6; i++) {
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
		return gameOver;
	}


	/////////////////////////////////////////// test
	public Game(InputStream in) {
		solarSystem = new SolarSystem();
		settlerTeam = new SettlerTeam(solarSystem.getBelt(),0);
		robotAi = new RobotAi();
		ufoAi = new UfoAi(solarSystem.getBelt(),0);
		megkergultGates = new MegkergultGates();

		choosableSettlers = new ArrayList<Integer>();
		//configOut(System.out);
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

	public void configIn(InputStream in) throws Exception {
		int state = 0; // 0-asteroids 1-sun 2-settlers 3-robots 4-ufos 5-gates
		Scanner sc = new Scanner(in);
		String line = null;
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			if(line.isEmpty()) state++;
			switch (state) {
			case 0:
				solarSystem.createField(line);
				break;
			case 1:
				sun = new Sun(line);
				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			default:
				break;
			}
		}
	}

	/////////////////////////////////////////// test

	public ArrayList<String> getActions() {
		ArrayList<String> actions = new ArrayList<String>();

		Asteroid currentAsteroid = chosenSettler.getAsteroid();
		ArrayList<AsteroidField> neighbors = currentAsteroid.getNeighbours();

		// Coordinate settlerCoordinates = chosenSettler.getAsteroid().getAsteroidField().getCoordinates();
		// actions.add(settlerCoordinates.toString());

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
			actions.add("place teleport gate");
		}

		if (currentAsteroid.isEmpty()) {
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

	public void placeTeleportGate(int i) {
		chosenSettler.placeTeleportGate(i);
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

	public void selectField(int idx) {
		selectedField = getField(idx);
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
}
