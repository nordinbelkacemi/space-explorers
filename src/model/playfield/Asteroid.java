package model.playfield;
import java.util.List;
import java.io.PrintStream;
import java.util.ArrayList;

import model.materials.Material;
import model.settler.Traveler;

/**
 * Az adott aszteroida köpenyének vastagságát és a magjában lévő nyersanyagot tárolja, valamint a rajta tartózkodó telepeseket és robotokat.
 * A felelőssége még, hogy felrobbanjon ha radioaktív a belseje és napközelben van, vagy elpárologtassa a jeget, ha az van benne.
 */
public class Asteroid {
	////////////////////////////////////////// attribútumok

	/** Az aszteroida belsejében esetlegesen elhelyezkedő nyersanyag. */
	private Material material;

	/** Az aszteroida rétegeinek számát tárolja. */
	private int thickness;

	/** Az aszteroida tároló mezője. */
	private AsteroidField field;

	/** Az aszteroidán tartózkodó travelereket (robotok vagy telepesek) tárolja. */
	private List<Traveler> travelers = new ArrayList<>();


	////////////////////////////////////////// konstruktorok

	/** Nem üres aszteroida */
	public Asteroid(Material m, int t, AsteroidField f) {
		material = m;
		thickness = t;
		field = f;
		travelers = new ArrayList<Traveler>();
	}

	/** Üres aszteroida */
	public Asteroid(int t, AsteroidField f) {
		material = null;
		thickness = t;
		field = f;
		travelers = new ArrayList<Traveler>();
	}

	//////////////////////////////////////////// függvények

	/**
	 * A paraméterként kapott Traveler-t hozzáadja a travelers tárolóhoz.
	 * @param t a kapott Traveler
	 */
	public void addTraveler(Traveler t) {
		travelers.add(t);
	}
	/**
	 * A paraméterként kapott Traveler-t eltávolítja a travelers tárolóból.
	 * @param t az eltávolítandó Traveler
	 */
	public void removeTraveler(Traveler t) {
		travelers.remove(t);
	}

	/** Eltávolít egyet az aszteroida rétegei közül, csökkenti a thickness változót eggyel. */
	public void removeLayer() {
		thickness--;
	}

	/**
	 * Az aszteroida magjában elhelyezi a paraméterként kapott nyersanyagot (a material tárolóhoz adja).
	 * @param m a kapott nyersanyag
	 */
	public void addMaterial(Material m) {
		material = m;
	}

	/**
	 * Eltávolítja az aszteroida magjából a nyersanyagot (a material tárolóból törli).
	 * @return a mag anyaga
	 */
	public Material removeMaterial() {
		Material m = material;
		material = null;
		return m;
	}

	/** Napvihar esetén meghívódik, és meghívja az összes rajta tartózkodó traveler ReactToFlare() függvényét. */
	public void reactToFlare() {
		for (Traveler traveler : travelers) {
			traveler.reactToFlare();
		}
	}

	/** A megfelelő feltételek fennállása esetén felrobbantja az aszteroidát, vagy elszublimáltatja a magjában lévő vízjeget. */
	public void checkDangers() {
		if(thickness == 0) {
			material.reactToSun(this);
		}
	}

	/**
	 * Visszaad egy, az aszteroidához tartozó AsteroidFieldet és annak szomszédait tartalmazó listát.
	 * @return a szomszédok listája
	 */
	public ArrayList<AsteroidField> getNeighbours() {
		return field.getNeighbours();
	}

	/** Az aszteroida felrobban, megsemmisítve ezzel magát és a rajta lévő utazókat. */
	public void explode() {
		for (Traveler traveler : travelers) {
			traveler.reactToExplosion();
		}
		field.removeAsteroid(this);
	}

	/** A jégből álló mag elszublimál. */
	public void sublime() {
		material = null;
	}

	/**
	 * Visszaadja az aszteroida AsteroidField-jét.
	 * @return az AsteroidField
	 */
	public AsteroidField getAsteroidField() {
		return field;
	}

	/**
	 * Beállítja az aszteroida AsteroidField-jét.
	 * @param af az AsteroidField
	 */
	public void setAsteroidField(AsteroidField af) {
		field = af;
	}

	/**
	 * Ez nem egy koordináta, hanem indexek.
	 * @return x: a field indexe y: az aszteroida indexe a fieldben
	 */
	public Coordinate getIndexes() {
		return field.getIndexes(this);
	}

	/**
	 * Visszaadja, hogy üreges-e az aszteroida.
	 * @return a logikai érték
	 */
	public boolean isEmpty() {
		return material == null;
	}

	/**
	 * Visszaadja az aszteroida köpenyének vastagságát.
	 * @return a köpeny vastagsága
	 */
	public int getThickness() {
		return thickness;
	}

	/**
	 * Kiírja a megadott PrintStream-re az általunk definiált config fájloknak megfelelő formátumban az aszteroida adatait.
	 * @param out ahova kiírja az adatokat
	 */
	public void printToConfig(PrintStream out) {
		if(material != null)
			out.print(material.toString());
		else
			out.print("empty ");
		out.print(thickness);
	}

	public Material getMaterial() {
		return material;
	}
}
