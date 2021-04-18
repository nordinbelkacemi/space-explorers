package controllers;

import java.io.PrintStream;

import model.ai.RobotAi;
import model.ai.UfoAi;
import model.playfield.MegkergultGates;
import model.playfield.SolarSystem;
import model.playfield.Sun;
import model.settler.SettlerTeam;

public class Game {
	private Sun sun;
	private SolarSystem ss;
	private MegkergultGates mg;
	private SettlerTeam st;
	private RobotAi rai;
	private UfoAi uai;

	public Game() {
		// playfield
		sun = new Sun();
		ss = new SolarSystem(sun);
		
		// settlers
		st = new SettlerTeam(ss.getBelt()); 
		// robot
		rai = new RobotAi();
		// ufo
		uai = new UfoAi(ss.getBelt());
		// megkergült
		mg = new MegkergultGates();
		
		
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
}
