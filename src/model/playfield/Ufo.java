package model.playfield;

import model.ai.UfoAi;
import model.settler.Miner;
import model.settler.Traveler;

/**
 * Az űrben garázdálkodó ufok osztálya.
 * Csak mozogni és nyersanyagot bányaszni (lopni) tudnak.
 */
public class Ufo extends Traveler implements Miner{

	/**
	 * Az ufot irányító központi vezető.
	 */
	private UfoAi ai;

	@Override
	public void mine() {
		asteroid.removeMaterial();
	}
}
