package model.settler;

import java.util.ArrayList;
import java.util.List;

/**
 * A telepesek tárolásáért és a játékossal való kapcsolatért felelős objektum.
 */
public class SettlerTeam {
	
	/**
	 * Az összes telepest tárolja.
	 */
	private List<Settler> settlers;
	
	/**
	 * Visszaadja a telepesek listájából a kiválasztott telepest.
	 * @param n A választott telepes sorszáma
	 * @return A sorszámnak megfelelő telepes
	 */
	public Settler chooseSettler(int n) {
		return null;
	}
	
	/**
	 * Kitörli a telepesek listájából a paraméterként átadott telepest.
	 * @param s A kitörlendő telepes.
	 */
	public void removeSettler(Settler s) {
		
	}
}
