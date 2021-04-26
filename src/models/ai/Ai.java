package models.ai;

/**
 * Egy olyan interface amely "irányíthatóvá" teszi az őt implementáló osztályokat.
 * Egy függvénye van a kontroll ami egy AI "gondolkodását" reprezentálja.
 */
public interface Ai {
	/** Ez irányítja az osztály adott részeit. */
	public void control();
}
