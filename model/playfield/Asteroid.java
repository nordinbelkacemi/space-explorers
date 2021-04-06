package model.playfield;

import java.util.ArrayList;

import controller.Program;
import model.materials.Material;
import model.settler.Traveler;
import controller.Program;

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
	 * A paraméterként kapott traveler-t hozzáadja a travelers tárolóhoz
	 * @param t a kapott traveler
	 */
	public void addTraveler(Traveler t) {
		Program.indent++;
		Program.print();
		travelers.add(t);
		System.out.println("Asteroid.addTraveler()");
		Program.indent--;
	}
	/**
	 * A paraméterként kapott traveler-t eltávolítja a travelers tárolóból
	 * @param t az eltávolítandó
	 */
	public void removeTraveler(Traveler t) {
		Program.indent++;
		Program.print();
		System.out.println("Asteroid.removeTraveler()");
		Program.indent--;
	}

	/**Eltávolít egyet az aszteroida rétegei közül, csökkenti a thickness változót eggyel */
	public void removeLayer() {
		Program.indent++;
		Program.print();
		System.out.println("Asteroid.removeLayer()");
		Program.indent--;
	}

	/**
	 * Az aszteroida magjában elhelyezi a paraméterként kapott nyersanyagot (a material tárolóhoz adja)
	 * @param m a kapott nyersanyag
	 */
	public void addMaterial(Material m) {
		Program.indent++;
		Program.print();
		System.out.println("Asteroid.addMaterial()");
		material = m;
		Program.indent--;
	}

	/**
	 * Eltávolítja az aszteroida magjából a nyersanyagot (a material tárolóból törli)
	 * @return a mag anyaga
	 */
	public Material removeMaterial() {
		Program.indent++;
		Program.print();
		System.out.println("Asteroid.removeMaterial()");
		Program.print();
		System.out.println("returns " + material);
		Program.indent--;
		return material;
	}

	/**Napvihar esetén meghívódik, és meghívja az összes rajta tartózkodó traveler ReactToFlare() függvényét */
	public void reactToFlare() {
		Program.indent++;
		Program.print();
		System.out.println("Asteroid.reactToFlare");
		for (Traveler t : travelers) {
			t.reactToFlare();
		}
		Program.indent--;
	}

	/**A megfelelő feltételek fennállása esetén felrobbantja az aszteroidát, vagy elszublimáltatja a magjában lévő vízjeget */
	public void checkDangers() {
		Program.indent++;
		Program.print();
		System.out.println("Asteroid.checkDanger()");
		if(material != null) {
			material.reactToSun(this);
		}
		Program.indent--;
	}

	/**
	 * Visszaad egy, az aszteroidához tartozó AsteroidFieldet és annak szomszédait tartalmazó listát
	 * @return a szomszédok listája
	 */
	public ArrayList<AsteroidField> getNeighbours() {
		Program.indent++;
		Program.print();
		System.out.println("Asteroid.getNeighbours()");
		AsteroidField af = new AsteroidField();
		af.AddAsteroid(new Asteroid());
		ArrayList<AsteroidField> neighbourFields = new ArrayList<AsteroidField>();
		neighbourFields.add(af);
		Program.indent--;
		return neighbourFields;
	}

	/**Az aszteroida felrobban, megsemmísítvee ezzel magát és a rajta lévő utazókat */
	public void explode() {
		Program.indent++;
		Program.print();
		System.out.println("Asteroid.explode()");
		Program.indent--;
	}

	/**A jégbőll álló mag elszublimál */
	public void sublime() {
		Program.indent++;
		Program.print();
		System.out.println("Asteroid.sublime()");
		Program.indent--;
	}
}
