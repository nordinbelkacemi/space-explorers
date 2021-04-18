package controllers;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import model.ai.RobotAi;
import model.ai.UfoAi;
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

	/** A kiválasztható/léptethetö telepesek száma. (pl. ha van 6 telepes, és az elsovel már léptunk, akkor ez a szám 5) */
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
		if (n < 0 || !choosableSettlers.contains(n)) {
			
		}
		chosenSettler = settlerTeam.chooseSettler(n);
	}

	/** A gameOver változó lekérdezö függvénye
	 * @return True ha a játék véget ért, egyébként False
	 */
	public boolean over() {
		return gameOver;
	}

}
