package model.playfield;

import java.util.ArrayList;
import java.util.List;

import model.settler.buildable.TeleportGate;

/**
 * A megkergült teleportkapukat tároló osztály.
 * Feladata, hogy kergítse az összes tárolt kaput az adott körben.
 */
public class MegkergultGates {
	
	//* A játék során megkergült kapuk tárolója */
	private List<TeleportGate> gates;
	
	
	public MegkergultGates() {
		gates = new ArrayList<>();
	}
	
	/** Ha egy kaput napkitörés ér, ezzel a függvényel teszzük őt megkergültté. */
	public void addGate(TeleportGate gate) {
		gates.add(gate);
	}
}
