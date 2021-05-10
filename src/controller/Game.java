package controller;

import java.util.ArrayList;
import java.util.List;

import model.ai.RobotAi;
import model.ai.UfoAi;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.playfield.SolarSystem;
import model.playfield.Sun;
import model.settler.Settler;
import model.settler.SettlerTeam;
import view.GameFrame;
import view.SpaceExplorersGui;

public final class Game {
	private static Game instance = new Game();
	private SpaceExplorersGui gui;

	private Sun sun;
	private SolarSystem solarSystem;
	private SettlerTeam settlerTeam;
	private RobotAi robotAi;
	private UfoAi ufoAi;

	private boolean gameOver;
	private boolean playerLost;

	/**
	 * A kiválasztható/léptethetö telepesek sorszámait tároló lista. (pl. ha van 6
	 * telepes és már léptünk az elsövel, akkor ennek a tartalma: null, 2, 3, 4, 5,
	 * 6)
	 */
	private List<Settler> selectableSettlers;

	/** telepes mozgása során kiválasztott cél asteroid field */
	private AsteroidField selectedField;

	/** telepes mozgása során kiválasztott cél aszteroida */
	private Asteroid selectedAsteroid;

	private Game() {
		sun = new Sun();
		solarSystem = new SolarSystem(sun);
		settlerTeam = new SettlerTeam(solarSystem.getBelt());
		robotAi = new RobotAi();
		ufoAi = new UfoAi(solarSystem.getBelt());
		selectableSettlers = new ArrayList<Settler>();
		initSelectableSettlers();
		gameOver = false;
	}

	public static Game getInstance() {
		return instance;
	}

	public void startGame() {
		log("GAME STARTED 🚀  Hello There!\n");
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
		gui.log(message + "\n");
	}

	public void endTurn() {
		step();
		SelectedSettler.getInstance().set(null);
		resetSelectableSettlers();
		selectedField = null;
		selectedAsteroid = null;
		if (checkEndConditions()) {
			if (playerLost)
				log("Game over, you lost!");
			else
				log("Game over, you won!");
		} else {
			log("\nNEW TURN");
		}
		gui.turnEnded();
	}

	public void checkTurnEnd() {
		boolean turnended = true;
		for (Settler settler : selectableSettlers) {
			if (settler != null) {
				turnended = false;
				break;
			}
		}
		if (turnended) {
			endTurn();
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

	public void resetSelectableSettlers() {
		selectableSettlers.clear();
		initSelectableSettlers();
	}

	private void initSelectableSettlers() {
		for (Settler settler : getSettlers()) {
			selectableSettlers.add(settler);
		}
	}

	public void step() {
		SolarSystem.megkergultGates.kergit();
		sun.performAction();
		robotAi.control();
		ufoAi.control();
	}

	public boolean isGameOver() {
		return gameOver;
	}
	
	public boolean playerLost() {
		return playerLost;
	}
	
	private boolean checkEndConditions() {
		if (settlerTeam.getSize() == 0) {
			playerLost = true;
			gameOver = true;
			return true;
		}
		else {
			List<AsteroidField> fields = solarSystem.getBelt();
			for (AsteroidField field : fields) {
				List<Asteroid> asteroids = field.getAsteroids();
				for (Asteroid asteroid : asteroids) {
					int ironCount = 0, coalCount = 0, iceCount = 0, uraniumCount = 0;
					for (Settler settler : settlerTeam.getSettlers()) {
						if (settler.getAsteroid() == asteroid) {
							ironCount += settler.getIronCount();
							coalCount += settler.getCoalCount();
							iceCount += settler.getIceCount();
							uraniumCount += settler.getUraniumCount();
						}
					}
					if (ironCount >= 3 && coalCount >= 3 && iceCount >= 3 && uraniumCount >= 3) {
						playerLost = false;
						gameOver = true;
						return true;
					}
				}
			}
		}
		return false;
	}
}