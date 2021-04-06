package controller;

import java.util.*;

import model.materials.*;
import model.settler.*;
import model.playfield.*;
import model.ai.*;
import model.settler.buildable.*;

public class Program {

	public static int indent = 0;

	public static void print() {
		for (int i = indent; i > 0; i--) {
			System.out.print("\t");
		}
	}

	static void settlerMoves() {
		// init
		Asteroid a = new Asteroid();
		Asteroid a2 = new Asteroid();
		Settler s = new Settler(a);

		// test
		System.out.println("\nTest:");
		s.move(a2);
	}

	static void settlerDrills() {
		// init
		Asteroid a = new Asteroid(3);
		Settler s = new Settler(a);

		// test
		System.out.println("\nTest:");
		s.drill();
	}

	static void settlerMinesIce() {
		// init
		Ice ice = new Ice();
		Asteroid a = new Asteroid(ice);
		Settler s = new Settler(a);

		// test
		System.out.println("\nTest:");
		s.mine();
	}

	static void settlerBuildsRobot() {
		// init
		Asteroid a = new Asteroid();
		Settler s = new Settler(a);
		AI ai = new AI();

		// test
		System.out.println("\nTest:");
		s.build(new Robot(a));
	}

	static void settlerBuildsTeleportGatePair() {
		// init
		Asteroid a = new Asteroid();
		Settler s = new Settler(a);
		TeleportGatePair tgp = new TeleportGatePair();

		// test
		System.out.println("\nTest:");
		s.build(tgp);
	}

	static void settlerPlacesFirstTeleportGate() {
		// init
		System.out.println("Setup:");
		Asteroid a = new Asteroid();
		Settler s = new Settler(a);
		AsteroidField af = new AsteroidField();
		af.AddAsteroid(a);
		a.setAsteroidField(af);
		s.build(new TeleportGatePair());

		// test
		System.out.println("\nTest:");
		s.placeTeleportGate();
	}

	static void settlerPlacesSecondTeleportGate() {
		// init
		System.out.println("Setup:");
		Asteroid a1 = new Asteroid();
		Asteroid a2 = new Asteroid();
		Settler s = new Settler(a1);
		AsteroidField af = new AsteroidField();
		af.AddAsteroid(a1);
		af.AddAsteroid(a2);
		a1.setAsteroidField(af);
		a2.setAsteroidField(af);
		s.build(new TeleportGatePair());
		s.placeTeleportGate();
		s.move(a2);

		// test
		System.out.println("\nTest:");
		s.placeTeleportGate();
	}

	static void settlerPutsIronBack() {
		// init
		Asteroid a = new Asteroid();
		Settler s = new Settler(a);
		s.addIron();

		// test
		System.out.println("\nTest:");
		s.putIronBack();

	}

	static void robotMoves() {
		// init
		Asteroid a = new Asteroid();
		Robot r = new Robot(a);
		Asteroid a2 = new Asteroid();

		// test
		System.out.println("\nTest:");
		r.move(a2);
	}

	static void robotDrills() {
		// init
		Asteroid a = new Asteroid(3);
		Robot r = new Robot(a);

		// test
		System.out.println("\nTest:");
		r.drill();
	}

	static void sunMoves() {
		// init
		System.out.println("Setup:");
		SolarSystem ss = new SolarSystem();

		Sun sun = new Sun();

		AsteroidField af = new AsteroidField();

		Asteroid aE = new Asteroid();
		Asteroid aU = new Asteroid();
		Asteroid aI = new Asteroid();

		Uranium u = new Uranium();
		Ice i = new Ice();

		aU.addMaterial(u);
		aI.addMaterial(i);

		af.AddAsteroid(aE);
		af.AddAsteroid(aU);
		af.AddAsteroid(aI);

		ss.addField(af);
		ss.setSun(sun);

		sun.setSolarSystem(ss);

		// test
		System.out.println("\nTest:");
		sun.move();
	}

	static void solarFlareStarts() {
		// init
		System.out.println("Setup:");
		SolarSystem ss = new SolarSystem();
		Sun sun = new Sun();
		AsteroidField af = new AsteroidField();
		Asteroid a = new Asteroid();
		Settler sr = new Settler(a);
		Robot r = new Robot(a);

		a.addTraveler(r);
		a.addTraveler(sr);
		af.AddAsteroid(a);
		ss.addField(af);
		ss.setSun(sun);
		sun.setSolarSystem(ss);

		// test
		System.out.println("\nTest:");
		sun.startFlare();
	}

	static void settlerReactsToExplosion() {
		// init
		Settler s = new Settler();
		
		// test
		System.out.println("\nTest:");
		s.reactToExplosion(); 

	}

	static void robotReactsToExplosion() {
		// setup
		AsteroidField af = new AsteroidField();
		Asteroid a1 = new Asteroid();
		Asteroid a2 = new Asteroid();
		af.AddAsteroid(a1);
		af.AddAsteroid(a2);
		Robot r = new Robot(a1);
		
		// test
		System.out.println("\nTest:");
		r.reactToExplosion();
	}


	static String[] useCases = {
		"1.\tSettler moves",
		"2.\tSettler drills",
		"3.\tSettler mines ice",
		"4.\tSettler builds robot",
		"5.\tSettler builds teleport gate pair",
		"6.\tSettler places first teleport gate",
		"7.\tSettler places second teleport gate",
		"8.\tSettler puts iron back",
		"9.\tRobot moves",
		"10.\tRobot drills",
		"11.\tSun moves",
		"12.\tSolar flare starts",
		"13.\tSettler reacts to explosion",
		"14.\tRobot reacts to explosion"
	};

	public static void main(String[] args) {
		System.out.println("ASZTEROIDABANYASZAT\n");
		System.out.println("Egy teszt futtatásához nyomja meg a megfelelő sorszámot (q-t a kilépéshez).\n");
		for (String useCase : useCases) {
			System.out.println(useCase);
		}

		Scanner sc = new Scanner(System.in);
		String input;
		while(!(input = sc.nextLine()).equals("q")) {
			switch (input) {
				case "1":
					settlerMoves();
					break;
				case "2":
					settlerDrills();
					break;
				case "3":
					settlerMinesIce();
					break;
				case "4":
					settlerBuildsRobot();
					break;
				case "5":
					settlerBuildsTeleportGatePair();
					break;
				case "6":
					settlerPlacesFirstTeleportGate();
					break;
				case "7":
					settlerPlacesSecondTeleportGate();
					break;
				case "8":
					settlerPutsIronBack();
					break;
				case "9":
					robotMoves();
					break;
				case "10":
					robotDrills();
					break;
				case "11":
					sunMoves();
					break;
				case "12":
					solarFlareStarts();
					break;
				case "13":
					settlerReactsToExplosion();
					break;
				case "14":
					robotReactsToExplosion();
					break;
				default:
					System.out.println("Rossz számot adtál meg!");
					break;
			}
		}
		sc.close();
	}
}
