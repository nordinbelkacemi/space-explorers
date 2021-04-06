package model.playfield;

import java.util.ArrayList;

import model.materials.Material;
import model.settler.Traveler;

/**
 * Az adott aszteroida köpenyének vastagságát és a magjában lévő nyersanyagot tárolja, valamint a rajta tartózkodó telepeseket és robotokat.
 * A felelőssége még, hogy felrobbanjon ha radioaktív a belseje és napközelben van, vagy elpárologtassa a jeget, ha az van benne.
 */
public class Asteroid {

	/**Az aszteroida belsejében esetlegesen elhelyezkedő nyersanyag */
	private Material material;

	/** Az aszteroida rétegeinek számát tárolja */
	private int thickness;

	/** Az aszteroida tároló mezője. */
	private AsteroidField field;

	/**Az aszteroidán tartózkodó travelereket (robotok vagy telepesek) tárolja */
	private ArrayList<Traveler> travelers = new ArrayList<>();

	public Asteroid(Material m) {
		material = m;
	}

	public Asteroid() {
		travelers = new ArrayList<Traveler>();
	}

	public Asteroid(int t) {
		thickness = t;
	}

	public AsteroidField getAsteroidField() {
		return field;
	}

	public void setAsteroidField(AsteroidField af) {
		field = af;
	}

	/**
	 * Aszteroidákat generál
	 * @return a generált aszteroidák
	 */
	private ArrayList<Asteroid> createAsteroids() {
		return null;
	}

	/**
	 * A paraméterként kapott traveler-t hozzáadja a travelers tárolóhoz
	 * @param t a kapott traveler
	 */
	public void addTraveler(Traveler t) {
	}
	/**
	 * A paraméterként kapott traveler-t eltávolítja a travelers tárolóból
	 * @param t az eltávolítandó
	 */
	public void removeTraveler(Traveler t) {
	}

	/**Eltávolít egyet az aszteroida rétegei közül, csökkenti a thickness változót eggyel */
	public void removeLayer() {
	}

	/**
	 * Az aszteroida magjában elhelyezi a paraméterként kapott nyersanyagot (a material tárolóhoz adja)
	 * @param m a kapott nyersanyag
	 */
	public void addMaterial(Material m) {
	}

	/**
	 * Eltávolítja az aszteroida magjából a nyersanyagot (a material tárolóból törli)
	 * @return a mag anyaga
	 */
	public Material removeMaterial() {
		return null;
	}

	/**Napvihar esetén meghívódik, és meghívja az összes rajta tartózkodó traveler ReactToFlare() függvényét */
	public void reactToFlare() {
	}

	/**A megfelelő feltételek fennállása esetén felrobbantja az aszteroidát, vagy elszublimáltatja a magjában lévő vízjeget */
	public void checkDangers() {
	}

	/**
	 * Visszaad egy, az aszteroidához tartozó AsteroidFieldet és annak szomszédait tartalmazó listát
	 * @return a szomszédok listája
	 */
	public ArrayList<AsteroidField> getNeighbours() {
		return null;
	}

	/**Az aszteroida felrobban, megsemmísítvee ezzel magát és a rajta lévő utazókat */
	public void explode() {
	}

	/**A jégbőll álló mag elszublimál */
	public void sublime() {
	}
}
