package model.settler.buildable;

import java.util.ArrayList;

import controller.Program;
import model.settler.Settler;

/**
 * Az összetartozó teleportkapukat reprezentálja,
 * a telepesek tudják megépíteni a teleportkapu-párokat.
 */
public class TeleportGatePair implements Buildable {

	/**
	 * A kapupárból az elkészített, de még le nem helyezett kapukat tárolja.
	 */
	private ArrayList<TeleportGate> gates = new ArrayList<>();
	
	public ArrayList<TeleportGate> getGates() {
		return gates;
	}
	
	/**
	 * Létrehoz két TeleportGate-t, hozzáadja őket a gates tárolóhoz,
	 * meghívja mindkét kapun a SetOtherGate függvényt a párjával paraméterezve,
	 * majd hozzáadja saját magát (a TeleportGatePair-t) a kapupárt építő
	 * Settler-hez a Settler StoreTeleportGate() függvényével.
	 * @param s Az a Settler, aki megépíti az adott eszközt.
	 */
	@Override
	public void build(Settler s) {
		Program.indent++;
		Program.print();
		System.out.println("TeleportGatePair.build()");
		s.getIron();
		s.getIron();
		s.getIce();
		s.getUranium();
		
		gates.add(new TeleportGate());
		gates.add(new TeleportGate());
		gates.get(0).setOtherGate(gates.get(1));
		gates.get(0).setAsteroidField(s.getAsteroid().getAsteroidField());
		gates.get(1).setOtherGate(gates.get(0));
		gates.get(1).setAsteroidField(s.getAsteroid().getAsteroidField());
		
		addTeleportGate(gates.get(0));
		addTeleportGate(gates.get(1));
		s.storeTeleportGatePair(this);
		Program.indent--;
	}
	
	/**
	 * A paraméterként kapott kaput hozzáadja a gates tárolóhoz.
	 * @param tg A tárolóhoz adandó teleportkapu.
	 */
	public void addTeleportGate(TeleportGate tg) {
		Program.indent++;
		Program.print();
		System.out.println("TeleportGatePair.addTeleportGate()");
		Program.indent--;
	}
	
	/**
	 * Kapu lehelyezésekor hívódik meg.
	 * Visszaad egy kaput a gates tömbből, majd eltávolítja azt a tárolóból.
	 * @return Az eltávolított teleportkapu.
	 */
	public TeleportGate removeTeleportGate() {
		Program.indent++;
		Program.print();
    	System.out.println("TeleportGate.removeTeleportGate()");
    	Program.indent--;
		return gates.remove(gates.size()-1);
	}

	
}
