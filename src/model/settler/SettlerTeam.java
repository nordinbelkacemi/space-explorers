package model.settler;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.playfield.AsteroidField;

/**
 * A telepesek tárolásáért és a játékossal való kapcsolatért felelős objektum.
 */
public class SettlerTeam {
	
	/**
	 * Az összes telepest tárolja.
	 */
	private List<Settler> settlers;
	
	/**
	 * Az aszteroidaöv.
	 */
	private List<AsteroidField> belt;

	/**
	 * Konstruktor.
	 * @param b az aszteroidaövet alkotó AsteroidField-ek listája
	 */
	public SettlerTeam(List<AsteroidField> b) {
		Settler.setTeam(this);
		Random r  = new Random();
		settlers = new ArrayList<>();
		belt = b;
		for (int i = 0; i < 6; i++) {
			settlers.add(new Settler(belt.get(r.nextInt(42)).getAsteroids().get(0)));
		}
	}
	
	/**
	 * Visszaadja a telepesek listájából a kiválasztott telepest.
	 * @param n a választott telepes sorszáma
	 * @return a sorszámnak megfelelő telepes
	 */
	public Settler chooseSettler(int n) {
		return settlers.get(n - 1);
	}
	
	/**
	 * Kitörli a telepesek listájából a paraméterként átadott telepest.
	 * @param s a kitörlendő telepes
	 */
	public void removeSettler(Settler s) {
		settlers.remove(s);
	}
	
	/**
	 * Kiírja a megadott PrintStream-re az általunk definiált config fájloknak megfelelő formátumban a SettlerTeam adatait.
	 * @param out ahova kiírja az adatokat
	 */
	public void configOut(PrintStream out) {
		for (Settler s : settlers) {
			s.printToConfig(out);
			out.println();
		}
	}
	
	////////////////////////////////////////// test
	public SettlerTeam(List<AsteroidField> b,int t) {
		belt = b;
		Settler.setTeam(this);
		settlers = new ArrayList<>();
	}
}
