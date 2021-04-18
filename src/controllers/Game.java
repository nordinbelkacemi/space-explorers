package controllers;


import java.io.PrintStream;
import java.util.List;

import console.Console;
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
	private int nChoosable;

	private Console console;

	public Game() {
		// playfield
		sun = new Sun();
		solarSystem = new SolarSystem(sun);
		
		// settlers
		settlerTeam = new SettlerTeam(ss.getBelt()); 
		// robot
		robotAi = new RobotAi();
		// ufo
		ufoAi = new UfoAi(ss.getBelt());
		// megkergült
		megkergultGates = new MegkergultGates();
		
		configOut(System.out);
	}
	
	public void configOut(PrintStream out) {
		ss.configOut(out);
		System.out.println();
		st.configOut(out);
		System.out.println();
		rai.configOut(out);
		System.out.println();
		uai.configOut(out);
	}

	/** A játékot elindító függvény */
	public void start() {
		gameOver = false;
	}

	/** 
	 * Egy telepest kiválasztó függvény: beállítja a chosenSettler-t a megfelelo telepesre 
	 * @param n A kiválasztott telepes sorszáma
	 */
	public void chooseSettler(int n) {
		chosenSettler = settlerTeam.chooseSettler(n);
		nChoosable -= 1;
	}

	/** A gameOver változó lekérdezö függvénye */
	public boolean over() {
		return gameOver;
	}

}
