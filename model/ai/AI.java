package model.ai;

import java.util.ArrayList;

import controller.Program;
import model.settler.buildable.Robot;

/**
 * A robotok tárolásáért és irányításáért felelős objektum.
 */
public class AI {
	
	/**
	 * A settlerek által épített és AI által vezérelt robotokat tartalmazza.
	 */
	private ArrayList<Robot> robots;
	
	/**
	* Meghatározza a robotok következő lépését és végre is hajtja azokat.
	*/
	public void control() {
		
	}

	public void addRobot(Robot r) {
		Program.indent++;
		Program.print();
		System.out.println("AI.addRobot()");
		Program.indent--;
	}
}
