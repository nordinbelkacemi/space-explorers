package model.playfield;

import java.io.PrintStream;

import model.ai.UfoAi;
import model.settler.Miner;
import model.settler.SettlerTeam;
import model.settler.Traveler;

/**
 * Az űrben garázdálkodó ufok osztálya.
 * Csak mozogni és nyersanyagot bányaszni (lopni) tudnak.
 */
public class Ufo extends Traveler implements Miner{

	/**
	 * Az ufót irányító központi vezető.
	 */
	private static UfoAi ai;
	
	public Ufo(Asteroid a) {
		setPosition(a);
	}

	/**
	 * A bányászást megvalósító metódus
	 */
	@Override
	public void mine() {
		asteroid.removeMaterial();
	}
	
	/**
	 * Beállítja az ufót irányító mesterséges intelligenciát.
	 * @param uai
	 */
	public static void setAi(UfoAi uai) {
    	ai = uai;
    }

	public void printToConfig(PrintStream out) {
		out.print(asteroid.getIndexes().toString());
    	
	}
}
