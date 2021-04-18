package model.ai;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import model.playfield.Ufo;
import model.settler.buildable.Robot;

/**
 * A robotok tárolásáért és irányításáért felelős objektum.
 */
public class RobotAi implements Ai {
	
	/**
	 * A settlerek által épített és AI által vezérelt robotokat tartalmazza.
	 */
	private List<Robot> robots;
	
	
	public RobotAi() {
		robots = new ArrayList<>();
	}
	
	/**
	* Meghatározza a robotok következő lépését és végre is hajtja azokat.
	*/
	public void control() {
		
	}

	public void addRobot(Robot r) {
		
	}
	
	public void configOut(PrintStream out) {
		for (Robot r : robots) {
			r.printToConfig(out);
			out.println();
		}
	}
}
