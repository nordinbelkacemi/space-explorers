package model.playfield;

import java.io.PrintStream;
import java.util.Iterator;

import model.ai.RobotAi;
import model.ai.UfoAi;
import model.settler.Miner;
import model.settler.SettlerTeam;
import model.settler.Traveler;

/**
 * Az űrben garázdálkodó ufok osztálya.
 * Csak mozogni és nyersanyagot bányaszni (lopni) tudnak.
 */
public class Ufo extends Traveler implements Miner {

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
	}

	/**
	 * A bányászást megvalósító metódus.
	 */
	@Override
	public void mine() {
		asteroid.removeMaterial();
	}
	
	/**
	 * Beállítja az ufót irányító mesterséges intelligenciát.
	 * @param uai a beállítandó UfoAi objektum
	 */
	public static void setAi(UfoAi uai) {
    	ai = uai;
    }

	/**
	 * Kiírja a megadott PrintStream-re az általunk definiált config fájloknak megfelelő formátumban az Ufo adatait.
	 * @param out ahova kiírja az adatokat
	 */
	public void printToConfig(PrintStream out) {
		out.print(asteroid.getIndexes().toString());
	}
	
	/**
	 * Felülírja az ős függvényét, az Ufok nem halnak meg napvihar hatására.
	 */
	@Override
	public String reactToFlare(Iterator<Traveler> ufoIter) { 
		return getDeathInfo();
	}
	
	@Override
	public String die() {
		ai.remove(this);
		return super.die();
	}

	public String getDeathInfo() {
		return new String("");
	}
}
