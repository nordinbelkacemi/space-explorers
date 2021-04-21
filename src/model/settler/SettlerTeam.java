package model.settler;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import console.exceptions.InvalidCmdException;
import model.materials.Coal;
import model.materials.Ice;
import model.materials.Iron;
import model.materials.Uranium;
import model.playfield.AsteroidField;

/**
 * A telepesek tárolásáért és a játékossal való kapcsolatért felelős objektum.
 */
public class SettlerTeam {
	
	/**
	 * Az összes telepest tárolja.
	 */
	private List<Settler> settlers;
	
	/**
	 * Az aszteroidaöv.
	 */
	private List<AsteroidField> belt;

	/**
	 * Konstruktor.
	 * @param b az aszteroidaövet alkotó AsteroidField-ek listája
	 */
	public SettlerTeam(List<AsteroidField> b) {
		Settler.setTeam(this);
		Random r  = new Random();
		settlers = new ArrayList<>();
		belt = b;
		for (int i = 0; i < 6; i++) {
			settlers.add(new Settler(belt.get(r.nextInt(42)).getAsteroids().get(0)));
		}
	}
	
	/**
	 * Visszaadja a telepesek listájából a kiválasztott telepest.
	 * @param n a választott telepes sorszáma
	 * @return a sorszámnak megfelelő telepes
	 */
	public Settler chooseSettler(int n) {
		return settlers.get(n - 1);
	}
	
	/**
	 * Kitörli a telepesek listájából a paraméterként átadott telepest.
	 * @param s a kitörlendő telepes
	 */
	public void removeSettler(Settler s) {
		settlers.remove(s);
	}
	
	/**
	 * Kiírja a megadott PrintStream-re az általunk definiált config fájloknak megfelelő formátumban a SettlerTeam adatait.
	 * @param out ahova kiírja az adatokat
	 */
	public void configOut(PrintStream out) {
		for (Settler s : settlers) {
			s.printToConfig(out);
			out.println();
		}
	}
	
	////////////////////////////////////////// test
	public SettlerTeam(List<AsteroidField> b,int t) {
		belt = b;
		Settler.setTeam(this);
		settlers = new ArrayList<>();
	}

	public List<Settler> getSettlers() {
		return settlers;
	}
	
	public void addSettler(String s) throws InvalidCmdException {
		String[] data=s.split(",");
		String[] asteroid = data[0].split(" ");
		Settler settler = new Settler(belt.get(Integer.parseInt(asteroid[0])).getAsteroids().get(Integer.parseInt(asteroid[1])));
		for (int i = 1; i < data.length; i++) {
			String[] material = data[i].split(" ");
			if (material.length == 3) {
				if(material[0].equals("uranium")) {
					for (int j = 0; j < Integer.parseInt(material[2]); j++) {
						settler.addUranium(new Uranium(Integer.parseInt(material[1])));
					}
				}
				else throw new InvalidCmdException();
			}
			else if(material.length == 2){
				for (int j = 0; j < Integer.parseInt(material[1]); j++) {
					switch (material[0]) {
						case "iron":
							settler.addIron(new Iron());
							break;
						case "ice":
							settler.addIce(new Ice());
							break;
						case "coal":
							settler.addCoal(new Coal());
							break;
						default:
							throw new InvalidCmdException();
					}
				}
			}
		}
		settlers.add(settler);
	}
}
