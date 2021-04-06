package model.settler.buildable;

import model.settler.Settler;

/**
 * A telepesek által megépíthető szerkezeteket csoportosítja,
 * minden építménynek implementálnia kell ezt az interfészt.
 */
public interface Buildable {

	/**
	 * Beállítja az építmény attribútumait és elveszi
	 * a Settler tárolójából a megépítéséhez szükséges nyersanyagokat.
	 * @param s Az a Settler, aki megépíti az adott eszközt.
	 */
	public void build(Settler s);
}
