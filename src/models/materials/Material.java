package models.materials;

import models.playfield.Asteroid;
import models.settler.Settler;

/**
 * Absztrakt ősosztály.
 * Egy, az aszteroidák magjában található nyersanyagot reprezentál.
 * Felelős a telepes megadott tárolójába berakni magát.
 * Reagál arra ha nap éri.
 */
public abstract class Material {
	/**
	 * A telepesnek a megfelelő tárolójához hozzáadja magát.
	 * @param s az a settler akinek a tárolójába elhelyazi magát
	 * */
	public abstract void store(Settler s);

	/**
	 * Megfelelően reagál a naphoz közeli létre.
	 * @param a az aszteroida amiben a nyersanyag elhelyezkedik és akinek továbbítja a reakcióját
     */
	public void reactToSun(Asteroid a) {}
}
