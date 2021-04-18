package controllers;

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
		//ss.configOut(System.out);
		
		// settlers
		st = new SettlerTeam(ss.getBelt()); 
		st.configOut(System.out);
		
	}
}
