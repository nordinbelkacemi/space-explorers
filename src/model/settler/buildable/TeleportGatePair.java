package model.settler.buildable;

import java.util.ArrayList;
import java.util.List;

import model.settler.Settler;

/**
 * Az összetartozó teleportkapukat reprezentálja,
 * a telepesek tudják megépíteni a teleportkapu-párokat.
 */
public class TeleportGatePair implements Buildable {

	/**
	 * A kapupárból az elkészített, de még le nem helyezett kapukat tárolja.
	 */
	private List<TeleportGate> gates = new ArrayList<>();

	/**
	 * Létrehoz két TeleportGate-t, hozzáadja őket a gates tárolóhoz,
	 * meghívja mindkét kapun a SetOtherGate függvényt a párjával paraméterezve,
	 * majd hozzáadja saját magát (a TeleportGatePair-t) a kapupárt építő
	 * Settler-hez a Settler StoreTeleportGate() függvényével.
	 * @param s Az a Settler, aki megépíti az adott eszközt.
	 */
	@Override
	public void build(Settler s) {
		s.getIron();
		s.getIron();
		s.getIce();
		s.getUranium();
		
		gates.add(new TeleportGate());
		gates.add(new TeleportGate());
		gates.get(0).setOtherGate(gates.get(1));
		//gates.get(0).setAsteroidField(s.getAsteroid().getAsteroidField());
		gates.get(1).setOtherGate(gates.get(0));
		//gates.get(1).setAsteroidField(s.getAsteroid().getAsteroidField());
		s.storeTeleportGatePair(this);
	}
	
	/**
	 * A paraméterként kapott kaput hozzáadja a gates tárolóhoz.
	 * @param tg A tárolóhoz adandó teleportkapu.
	 */
	public void addTeleportGate(TeleportGate tg) {
		gates.add(tg);
	}
	
	/**
	 * Kapu lehelyezésekor hívódik meg.
	 * Visszaad egy kaput a gates tömbből, majd eltávolítja azt a tárolóból.
	 * @return Az eltávolított teleportkapu.
	 */
	public TeleportGate removeTeleportGate() {
		return gates.remove(gates.size()-1);
	}
	
	/**
	 * Visszaadja a pár még nem lerakott teleportkapuinak számát.
	 * @return A még nem lerakott teleportkapuinak száma
	 */
	public int getCount() {
		return gates.size();
	}
	
	/** Visszaadja a pár még nem lerakott teleportkapuinak listáját.
	 * @return A pár még nem lerakott teleportkapuinak listája.
	 */
	public List<TeleportGate> getGates() {
		return gates;
	}
}
