package model.playfield;

/** Egy hexagon pozícióját fejezi ki, két koordinátával. */
public class Coordinate {
    /**abszcissza */
    private int x;

    /**ordináta */
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
    	return x + " " + y;
    }
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public void setY(int y) {
    	this.y = y;
    }
}
