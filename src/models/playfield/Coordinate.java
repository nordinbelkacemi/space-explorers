package models.playfield;

/** Egy hexagon pozícióját fejezi ki, két koordinátával. */
public class Coordinate {
	
    /**abszcissza */
    private int x;

    /**ordináta */
    private int y;

    /**
     * Konstruktor.
     * @param x a beállítandó abszcissza
     * @param y a beállítandó ordináta
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
	 * Visszaadja a koordináta String reprezentációját.
	 * @return a String
	 */
    @Override
    public String toString() {
    	return x + " " + y;
    }
    
    /**
     * Visszaadja az x koordinátát
     * @return az x koordináta
     */
    public int getX() {
    	return x;
    }
    
    /**
     * Visszaadja az x koordinátát
     * @return az x koordináta
     */
    public int getY() {
    	return y;
    }
    
    /**
     * Beállítja az x koordinátát
     * @param x az x koordináta
     */
    public void setX(int x) {
    	this.x = x;
    }
    
    /**
     * Beállítja az y koordinátát
     * @param y az y koordináta
     */
    public void setY(int y) {
    	this.y = y;
    }
}
