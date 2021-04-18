package controllers;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import model.ai.RobotAi;
import model.ai.UfoAi;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.playfield.Coordinate;
import model.playfield.MegkergultGates;
import model.playfield.SolarSystem;
import model.playfield.Sun;
import model.settler.Settler;
import model.settler.SettlerTeam;

public class Game {
	private Sun sun;
	private SolarSystem solarSystem;
	private MegkergultGates megkergultGates;
	private SettlerTeam settlerTeam;
	private RobotAi robotAi;
	private UfoAi ufoAi;

	/** A játék végét jelzö valtozó: Ha vége a játék, akkor az értéke True, különben false */
	private boolean gameOver;

	/** A kiválasztott telepes. Ezzel a telepessel léphet a felhasználó (move, drill stb) */
	private Settler chosenSettler;

	/** A kiválasztható/léptethetö telepesek sorszámait tároló lista. (pl. ha van 6 telepes és már léptünk az elsövel, akkor ennek a tartalma: null, 2, 3, 4, 5, 6) */
	private List<Integer> choosableSettlers;

	// private Console console;

	public Game() {
		// playfield
		sun = new Sun();
		solarSystem = new SolarSystem(sun);
		
		// settlers
		settlerTeam = new SettlerTeam(solarSystem.getBelt()); 
		
		choosableSettlers = new ArrayList<Integer>();
		// robot
		robotAi = new RobotAi();
		// ufo
		ufoAi = new UfoAi(solarSystem.getBelt());
		// megkergült
		megkergultGates = new MegkergultGates();
		
		configOut(System.out);
	}
	
	public void configOut(PrintStream out) {
		solarSystem.configOut(out);
		System.out.println();
		settlerTeam.configOut(out);
		System.out.println();
		robotAi.configOut(out);
		System.out.println();
		ufoAi.configOut(out);
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
	public void chooseSettler(int n) throws Exception {
		if (!choosableSettlers.contains(n)) {
			throw new Exception();
		}
		chosenSettler = settlerTeam.chooseSettler(n);
	}

	/** A gameOver változó lekérdezö függvénye
	 * @return True ha a játék véget ért, egyébként False
	 */
	public boolean over() {
		return gameOver;
	}

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
}
