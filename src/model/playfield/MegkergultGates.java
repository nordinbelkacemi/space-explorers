package model.playfield;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.settler.buildable.TeleportGate;

/**
 * A megkergült teleportkapukat tároló osztály.
 * Feladata, hogy kergítse az összes tárolt kaput az adott körben.
 */
public class MegkergultGates {
	
	//* A játék során megkergült kapuk tárolója. */
	private List<TeleportGate> gates;
	
	Random r = new Random();
	
	/**
	 * Konstruktor.
	 */
	public MegkergultGates() {
		gates = new ArrayList<>();
	}
	
	/** Áthelyezi a kaput egy véletlenszerűen választott szomszédos aszteroidamezőre. */
	public void kergit() {
		for (TeleportGate gate : gates) {
			gate.getAsteroidField().removeNeighbour(gate.getOtherGate().getAsteroidField());
			gate.getOtherGate().getAsteroidField().removeNeighbour(gate.getAsteroidField());
			
			List<AsteroidField> neighbourFields = gate.getAsteroidField().getNeighbours();
			AsteroidField randomField = neighbourFields.get(r.nextInt(neighbourFields.size()-1));
			gate.getAsteroidField().removeTeleportGate(gate);
			gate.setAsteroidField(randomField);
			randomField.addTeleportGate(gate);
			randomField.addNeighbour(gate.getOtherGate().getAsteroidField());
			gate.getOtherGate().getAsteroidField().addNeighbour(randomField);
		}
	}
	
	/** Ha egy kaput napkitörés ér, ezzel a függvényel tesszük őt megkergültté. */
	public void addGate(TeleportGate gate) {
		gates.add(gate);
	}
}
