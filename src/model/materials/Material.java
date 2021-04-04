package model.materials;

import model.playfield.Asteroid;
import model.settler.Settler;

/**
 * Absztrakt ősosztály.
 * Egy, az aszteroidák magjában található nyersanyagot reprezentál.
 * Felelős a telepes megadott tárolójába berakni magát.
 * Reagál arra ha nap éri.
 */
public abstract class Material {
	/**
	 * A telepesnek a megfelelő tárolójához hozzáadja magát.
	 * @param s Az a settler akinek a tárolójába elhelyazi magát.
	 * */
	public abstract void store(Settler s);

	/**
	 * Megfelelően reagál a naphoz közeli létre.
	 * @param a Az az aszteroida akinek továbbítja a reakcióját.
     */
	public void reactToSun(Asteroid a) {}
}
