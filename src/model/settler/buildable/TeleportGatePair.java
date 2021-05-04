package model.settler.buildable;

import java.util.ArrayList;
import java.util.List;

import model.playfield.AsteroidField;
import model.playfield.MegkergultGates;
import model.settler.Settler;
import model.settler.SettlerTeam;

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
	 * @param s az a Settler, aki megépíti az adott eszközt
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
	 * @param tg a tárolóhoz adandó teleportkapu
	 */
	public void addTeleportGate(TeleportGate tg) {
		gates.add(tg);
	}
	
	/**
	 * Kapu lehelyezésekor hívódik meg.
	 * Visszaad egy kaput a gates tömbből, majd eltávolítja azt a tárolóból.
	 * @return az eltávolított teleportkapu
	 */
	public TeleportGate removeTeleportGate() {
		return gates.remove(gates.size()-1);
	}
	
	/**
	 * Visszaadja a pár még nem lerakott teleportkapuinak számát.
	 * @return a még nem lerakott teleportkapuinak száma
	 */
	public int getCount() {
		return gates.size();
	}
	
	/** Visszaadja a pár még nem lerakott teleportkapuinak listáját.
	 * @return a pár még nem lerakott teleportkapuinak listája
	 */
	public List<TeleportGate> getGates() {
		return gates;
	}
	
	////////////////////////////////////////////////////// test

	public TeleportGatePair() {

	}
	
	public TeleportGatePair(String s, SettlerTeam st, List<AsteroidField> belt, MegkergultGates kergult) {
		TeleportGate teleportgate1 = new TeleportGate();
		TeleportGate teleportgate2 = new TeleportGate();
		gates.add(teleportgate1);
		gates.add(teleportgate1);
		gates.get(0).setOtherGate(gates.get(1));
		gates.get(1).setOtherGate(gates.get(0));
		
		String[] data =s.split(",");
		if (data.length == 1) {
			String[] gate = data[0].split(" ");
			if(gate[0] == "settler") {
				st.chooseSettler(Integer.parseInt(gate[1])).storeTeleportGatePair(this);
			}
		}
		else if(data.length == 2){
			String[] gate1 =data[0].split(" ");
			if (gate1[0].equals("field")) {
				int x = 1;
				if (gate1.length == 3) {
					kergult.addGate(teleportgate1);
				}
				belt.get(Integer.parseInt(gate1[x])).addTeleportGate(removeTeleportGate());
			}
			
			String[] gate2 =data[1].split(" ");
			if(gate2[0].equals("field")) {
				int x = 1;
				if(gate2.length == 3) {
					kergult.addGate(teleportgate2);
				}
				belt.get(Integer.parseInt(gate1[x])).addTeleportGate(removeTeleportGate());
			}
			else if (gate2[0].equals("settler")) {
				st.chooseSettler(Integer.parseInt(gate2[1])).storeTeleportGatePair(this);
			}
			
		}
	}
}
