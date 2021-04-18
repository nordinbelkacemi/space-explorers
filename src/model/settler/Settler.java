package model.settler;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import model.materials.Coal;
import model.materials.Ice;
import model.materials.Iron;
import model.materials.Material;
import model.materials.Uranium;
import model.settler.buildable.Buildable;
import model.settler.buildable.TeleportGate;
import model.settler.buildable.TeleportGatePair;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;

/**
 * A játékos által irányított telepes.
 * Felel az eszköztárában szereplő eszközökért és anyagokért (visszatevés, építés),
 * a műveleteiért (mozgás, fúrás, bányászat).
 */
public class Settler extends Traveler implements Miner,Driller{

	//////////////////////////////////////// attributumok
	/** Az eszköztárában lévő Iron típusú nyersanyagok. */
    private List<Iron> ironStorage;

    /** Az eszköztárában lévő Uranium típusú nyersanyagok. */
    private List<Uranium> uraniumStorage;

    /** Az eszköztárában lévő Coal típusú nyersanyagok. */
    private List<Coal> coalStorage;

    /** Az eszköztárában lévő Ice típusú nyersanyagok. */
    private List<Ice> iceStorage;

    /** Az eszköztárában lévő nyersanyagok száma */
    private int materialCount;
    
    private static int capacity = 10;

    /** A telepes teleportkapu-párja. Ha nem épített kapupárt akkor null. */
    private List<TeleportGatePair> teleportGatePairs;

    /** A telepes csapata. */
    private SettlerTeam team;

    
    //////////////////////////////////////// ctors
    public Settler(Asteroid a,SettlerTeam st) {
        asteroid = a;
        uraniumStorage = new ArrayList<>();
        coalStorage = new ArrayList<>();
        iceStorage = new ArrayList<>();
        ironStorage = new ArrayList<>();
        team = st;
        materialCount = 0;
        teleportGatePairs = new ArrayList<>();
        a.addTraveler(this);
    }

    public Settler() {}

    
    
    //////////////////////////////////////// függvények
	  @Override
    public String toString() {
        return "Settler";
    }

    /**
     *  A bányászást megvalósító metódus.
     */
    public void mine() {
        System.out.println("Settler.mine()");
        Material m = asteroid.removeMaterial();
        m.store(this);
    }

    /**
     * Meghívja a paraméterben megkapott Buildable interfészt megvalósító objektum Build() függvényét,
     * ezzel megépíti az adott dolgot.
     * @param b
     */
    public void build(Buildable b) {

    	System.out.println("Settler.build()");
    	b.build(this);

    }

    /**
     * Eltárolja a paraméterben megadott kapupárt.
     */
    public void storeTeleportGatePair(TeleportGatePair tgp) {


    }
    
    /**
     * Kitörli a teleportGatePair-ben tárolt kapupárt.
     */
    public void removeTeleportGatePair() {

		System.out.println("Settler.removeTeleportGatePair()");

    }

    /**
     * Elhelyez egy teleportkaput azon az AsteroidField-en, amelyen a telepes elhelyezkedik.
     */
    public void placeTeleportGate() {
/*
		System.out.println("Settler.placeTeleportGate()");
    	TeleportGate tg = teleportGatePair.removeTeleportGate();
    	tg.setAsteroidField(this.asteroid.getAsteroidField());
    	AsteroidField af = this.asteroid.getAsteroidField();
    	af.addTeleportGate(tg);
    	if (teleportGatePair.getGates().size() == 0) {
    		TeleportGate firstTg = tg.getOtherGate();
    		AsteroidField firstAf = firstTg.getAsteroidField();
    		af.addNeighbour(firstAf);
    		firstAf.addNeighbour(af);
    		removeTeleportGatePair();
    	}*/

    }

    /** Hozzáadja a paraméterként kapott vasat a telepes ironStorage tárolójához, valamint eggyel növeli a materialCount-ot.*/
    public void addIron(Iron i) {}

    /** Hozzáadja a paraméterként kapott jeget a telepes iceStorage tárolójához, valamint eggyel növeli a materialCount-ot. */
    public void addIce(Ice i) {

        System.out.println("Settler.addIce()");

    }

    /** Hozzáadja a paraméterként kapott uránt a telepes uraniumStorage tárolójához, valamint eggyel növeli a materialCount-ot. */
    public void addUranium(Uranium u) {}

    /** Hozzáadja a paraméterként kapott szenet a telepes coalStorage tárolójához, valamint eggyel növeli a materialCount-ot. */
    public void addCoal(Coal c) {}

    /**
     * Eltávolít egy vasat a tárolójából és visszaadja.
     * @return egy Iron objektum
     */
    public Iron getIron() { 

        return null;
    }
  
    /**
     * Eltávolít egy uránt a tárolójából és visszaadja.
     * @return egy Uranium objektum
     */
    public Uranium getUranium() {

        return null;
    }

    /**
     * Eltávolít egy jeget a tárolójából és visszaadja.
     * @return egy Ice objektum
     */
    public Ice getIce() { return null; }

    /**
     * Eltávolít egy szenet a tárolójából és visszaadja.
     * @return egy Coal objektum
     */
    public Coal getCoal() {

        return null;
    }

    /**  Visszarak egy egységnyi vasat annak az aszteroidának a magjába, amin éppen tartózkodik. */
    public void putIronBack() {

        asteroid.addMaterial(getIron());

    }

    /**  Visszarak egy egységnyi uránt annak az aszteroidának a magjába, amin éppen tartózkodik. */
    public void putUraniumBack() {}

    /**  Visszarak egy egységnyi jeget annak az aszteroidának a magjába, amin éppen tartózkodik. */
    public void putIceBack() {}

    /**  Visszarak egy egységnyi szenet annak az aszteroidának a magjába, amin éppen tartózkodik. */
    public void putCoalBack() {}
    
    @Override
    public void reactToExplosion() {
        die();
    }
    
    public void printToConfig(PrintStream out) {
		out.print(asteroid.getIndexes().toString());
    	
    	
    	// material cuccokat majd később
	}
}
