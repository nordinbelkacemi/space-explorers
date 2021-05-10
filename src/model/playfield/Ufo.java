package model.playfield;

import java.util.Iterator;

import controller.Game;
import model.ai.UfoAi;
import model.materials.Material;
import model.settler.Miner;
import model.settler.Traveler;

/**
 * Az űrben garázdálkodó ufok osztálya.
 * Csak mozogni és nyersanyagot bányaszni (lopni) tudnak.
 */
public class Ufo extends Traveler implements Miner {

	private static int idCount = 0;
	
	/**
	 * Az ufót irányító központi vezető.
	 */
	private static UfoAi ai;
	
	/**
	 * Konstruktor.
	 * @param a az aszteroida, amelyiken tartózkodik az Ufo
	 */
	public Ufo(Asteroid a) {
		super(a);
		id = idCount++;
	}

	/**
	 * A bányászást megvalósító metódus.
	 */
	@Override
	public void mine() {
		Material m = asteroid.removeMaterial();
		Coordinate i = asteroid.getIndexes();
		Game.getInstance().log(toString() + " " + getId() + " STOLE " + m.toString() + " at field " + i.getX() + " asteroid " + i.getY());
	}
	
	/**
	 * Beállítja az ufót irányító mesterséges intelligenciát.
	 * @param uai a beállítandó UfoAi objektum
	 */
	public static void setAi(UfoAi uai) {
    	ai = uai;
    }
	
	/**
	 * Felülírja az ős függvényét, az Ufok nem halnak meg napvihar hatására.
	 */
	@Override
	public void reactToFlare(Iterator<Traveler> ufoIter) { 
		
	}
	
	@Override
	public void die() {
		ai.remove(this);
		super.die();
	}
	
	@Override
	public String toString() {
		return "ufo";
	}
}
