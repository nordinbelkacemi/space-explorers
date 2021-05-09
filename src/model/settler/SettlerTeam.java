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
			Settler settler = new Settler(belt.get(r.nextInt(42)).getAsteroids().get(0));
			settler.setId(i + 1);
			settlers.add(settler);
		}
	}

	/**
	 * Kitörli a telepesek listájából a paraméterként átadott telepest.
	 * @param s a kitörlendő telepes
	 */
	public void removeSettler(Settler s) {
		settlers.remove(s);
	}

	public List<Settler> getSettlers() {
		return settlers;
	}

	public int getSize() {
		return settlers.size();
	}
}
