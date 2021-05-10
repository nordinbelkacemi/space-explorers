package model.settler;

import java.util.ArrayList;
import java.util.List;

import model.materials.Coal;
import model.materials.Ice;
import model.materials.Iron;
import model.materials.Material;
import model.materials.Uranium;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.settler.buildable.Buildable;
import model.settler.buildable.TeleportGate;
import model.settler.buildable.TeleportGatePair;

/**
 * A játékos által irányított telepes.
 * Felel az eszköztárában szereplő eszközökért és anyagokért (visszatevés, építés),
 * a műveleteiért (mozgás, fúrás, bányászat).
 */
public class Settler extends Traveler implements Miner, Driller {

	//////////////////////////////////////// attribútumok
	/** Az eszköztárában lévő Iron típusú nyersanyagok. */
    private List<Iron> ironStorage;

    /** Az eszköztárában lévő Uranium típusú nyersanyagok. */
    private List<Uranium> uraniumStorage;

    /** Az eszköztárában lévő Coal típusú nyersanyagok. */
    private List<Coal> coalStorage;

    /** Az eszköztárában lévő Ice típusú nyersanyagok. */
    private List<Ice> iceStorage;
    
    /** A telepes eszköztárának kapacitása. */
    private static int capacity = 10;

    /** A telepes teleportkapu-párja. Ha nem épített kapupárt akkor null. */
    private List<TeleportGatePair> teleportGatePairs;

    /** A telepes csapata. */
    private static SettlerTeam team;
    
    //////////////////////////////////////// konstruktorok

    /**
	 * Konstruktor.
	 * @param a az aszteroida, amelyiken a telepes tartózkodik.
	 */
    public Settler(Asteroid a) {
        super(a);
        uraniumStorage = new ArrayList<>();
        coalStorage = new ArrayList<>();
        iceStorage = new ArrayList<>();
        ironStorage = new ArrayList<>();
        teleportGatePairs = new ArrayList<>();
    }

    //////////////////////////////////////// függvények
    /**
	 * Visszaadja a telepes String reprezentációját.
	 * @return a String
	 */
	@Override
    public String toString() {
        return "settler";
    }

    /**
     *  A bányászást megvalósító metódus.
     */
    public void mine() {
        Material m = asteroid.removeMaterial();
        m.store(this);
    }
    
    /**
     *  A fúrást megvalósító metódus.
     */
    public void drill() {
        asteroid.removeLayer();
    }

    /**
     * Meghívja a paraméterben megkapott Buildable interfészt megvalósító objektum Build() függvényét,
     * ezzel megépíti az adott dolgot.
     * @param b a megépítendő objektum
     */
    public void build(Buildable b) {
    	b.build(this);
    }

    /**
     * Eltárolja a paraméterben megadott kapupárt.
     */
    public void storeTeleportGatePair(TeleportGatePair tgp) {
    	teleportGatePairs.add(tgp);
    }
    
    /**
     * Kitörli a teleportGatePair-ben tárolt kapupárt.
     */
    public void removeTeleportGatePair() {
    	teleportGatePairs.remove(0);
    }

    /**
     * Elhelyez egy teleportkaput azon az AsteroidField-en, amelyen a telepes elhelyezkedik.
     */
    public void placeTeleportGate() {
    	TeleportGatePair pair = teleportGatePairs.get(0);
    	TeleportGate tg = pair.removeTeleportGate();
    	AsteroidField af = this.asteroid.getAsteroidField();
    	tg.setAsteroidField(af);
    	af.addTeleportGate(tg);
    	if (pair.getGates().isEmpty()) {
    		TeleportGate firstTg = tg.getOtherGate();
    		AsteroidField firstAf = firstTg.getAsteroidField();
    		af.addNeighbour(firstAf);
    		firstAf.addNeighbour(af);
    		removeTeleportGatePair();
    	}
    }

    /** Hozzáadja a paraméterként kapott vasat a telepes ironStorage tárolójához, valamint eggyel növeli a materialCount-ot.*/
    public void addIron(Iron i) {
    	ironStorage.add(i);
    }

    /** Hozzáadja a paraméterként kapott jeget a telepes iceStorage tárolójához, valamint eggyel növeli a materialCount-ot. */
    public void addIce(Ice i) {
    	iceStorage.add(i);
    }

    /** Hozzáadja a paraméterként kapott uránt a telepes uraniumStorage tárolójához, valamint eggyel növeli a materialCount-ot. */
    public void addUranium(Uranium u) {
    	uraniumStorage.add(u);
    }

    /** Hozzáadja a paraméterként kapott szenet a telepes coalStorage tárolójához, valamint eggyel növeli a materialCount-ot. */
    public void addCoal(Coal c) {
    	coalStorage.add(c);
    }

    /**
     * Eltávolít egy vasat a tárolójából és visszaadja.
     * @return egy Iron objektum
     */
    public Iron getIron() { 
    	return ironStorage.remove(0);
    }
  
    /**
     * Eltávolít egy uránt a tárolójából és visszaadja.
     * @return egy Uranium objektum
     */
    public Uranium getUranium() {
    	return uraniumStorage.remove(0);
    }

    /**
     * Eltávolít egy jeget a tárolójából és visszaadja.
     * @return egy Ice objektum
     */
    public Ice getIce() {
    	return iceStorage.remove(0);
    }

    /**
     * Eltávolít egy szenet a tárolójából és visszaadja.
     * @return egy Coal objektum
     */
    public Coal getCoal() {
    	return coalStorage.remove(0);
    }

    /**  Visszarak egy egységnyi vasat annak az aszteroidának a magjába, amin éppen tartózkodik. */
    public void putIronBack() {
        asteroid.addMaterial(getIron());
    }

    /**  Visszarak egy egységnyi uránt annak az aszteroidának a magjába, amin éppen tartózkodik. */
    public void putUraniumBack() {
    	asteroid.addMaterial(getUranium());
    }

    /**  Visszarak egy egységnyi jeget annak az aszteroidának a magjába, amin éppen tartózkodik. */
    public void putIceBack() {
    	asteroid.addMaterial(getIce());
    }

    /**  Visszarak egy egységnyi szenet annak az aszteroidának a magjába, amin éppen tartózkodik. */
    public void putCoalBack() {
    	asteroid.addMaterial(getCoal());
    }
    
    /**
     * Megöli a telepest.
     */
    @Override
    public void die() {
        super.die();
    	team.removeSettler(this);
    }

    /**
     * Beállítja a telepes csapatát.
     * @param st a beállítandó csapat
     */
    public static void setTeam(SettlerTeam st) {
    	team = st;
    }

    /**
     * Visszaadja, hogy a telepes tud-e teleportkapukat építeni.
     * @return a logikai érték
     */
    public boolean canBuildGate() {
    	int size = teleportGatePairs.size();
    	if(ironStorage.size() >= 2 && iceStorage.size() >= 1 && uraniumStorage.size() >= 1 && size < 2) {
    		if(size == 0)
    			return true;
    		if(size == 1 && teleportGatePairs.get(0).getGates().size() == 1) 
    			 return true;
    	}
        return false;
    }

    /**
     * Visszaadja, hogy a telepes tud-e robotot építeni.
     * @return a logikai érték
     */
    public boolean canBuildRobot() {
        return ironStorage.size() >= 1 && coalStorage.size() >= 1 && uraniumStorage.size() >= 1;
    }

    /**
     * Visszaadja, hogy a telepes tud-e teleportkaput lehelyezni.
     * @return a logikai érték
     */
    public boolean canPlaceGate() {
        return teleportGatePairs.size() >= 1;
    }
    
    public int getNumberofTeleportgatePairs() {
    	return teleportGatePairs.size();
    }

    /**
     * Visszaadja, hogy a telepesnél hány egység vas van.
     * @return a telepesnél lévő egységek száma
     */
    public int getIronCount() {
        return ironStorage.size();
    }

    /**
     * Visszaadja, hogy a telepesnél hány egység szén van.
     * @return a telepesnél lévő egységek száma
     */
    public int getCoalCount() {
        return coalStorage.size();
    }

    /**
     * Visszaadja, hogy a telepesnél hány egység vízjég van.
     * @return a telepesnél lévő egységek száma
     */
    public int getIceCount() {
        return iceStorage.size();
    }

    /**
     * Visszaadja, hogy a telepesnél hány egység urán van.
     * @return a telepesnél lévő egységek száma
     */
    public int getUraniumCount() {
        return uraniumStorage.size();
    }
    
    public int getMaterialCount() {
    	return getIronCount() + getCoalCount() + getIceCount() + getUraniumCount();
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<TeleportGatePair> getTeleportGatePairs() {
		return teleportGatePairs;
    }
    
    public int getCapacity() {
    	return capacity;
    }
}   
