package controller;

import java.util.ArrayList;
import java.util.List;

import model.playfield.Asteroid;
import model.playfield.AsteroidField;
import model.settler.Settler;
import model.settler.buildable.Robot;
import model.settler.buildable.TeleportGatePair;
import view.GameFrame;
import view.SpaceExplorersGui;

public class SelectedSettler {
    private static SelectedSettler instance = new SelectedSettler(null);
    private Settler settler;
    private SpaceExplorersGui gui;

    private SelectedSettler(Settler settler) {
        /* TODO Game.ctor where/how to set gui ? */
        set(settler);
    }

    public static SelectedSettler getInstance() {
        return instance;
    }

    /** Ezt a fuggvényt így kell meghivni: SelectedSettler.getInstance().set(...) */
    public void set(Settler settler) {
        this.settler = settler;
    }
    
    public List<String> getActions() {
 	 	List<String> actions = new ArrayList<String>();

 	 	Asteroid currentAsteroid = settler.getAsteroid();
 	 	List<AsteroidField> neighbors = currentAsteroid.getNeighbours();

 	 	for (AsteroidField af : neighbors) {
 	 		if (af.getAsteroids().size() > 0) {
 	 			actions.add("move");
 	 			break;
 	 		}
 	 	}
 	 	
 	 	if (currentAsteroid.getThickness() > 0) {
 	 		actions.add("drill");
 	 	}

 	 	if (currentAsteroid.getThickness() == 0 && !currentAsteroid.isEmpty()) {
 	 		actions.add("mine");
 	 	}

 	 	if (settler.canBuildRobot()) {
 	 		actions.add("build robot");
 	 	}
 	 	
 	 	if (settler.canBuildGate()) {
 	 		actions.add("build teleportgate");
 	 	}

 	 	if (settler.canPlaceGate()) {
 	 		actions.add("place teleportgate");
 	 	}

 	 	if (currentAsteroid.isEmpty() && currentAsteroid.getThickness() == 0) {
 	 		if (settler.getIronCount() >= 1) {
 	 			actions.add("putback iron");
 	 		}
 	 		if (settler.getUraniumCount() >= 1) {
 	 			actions.add("putback uranium");
 	 		}
 	 		if (settler.getCoalCount() >= 1) {
 	 			actions.add("putback coal");
 	 		}
 	 		if (settler.getIceCount() >= 1) {
 	 			actions.add("putback ice");
 	 		}
 	 	}
 	 	return actions;
    }
    
    public void performAction() {
        int idx = Game.getInstance().getSelectableSettlers().indexOf(settler);
        Game.getInstance().getSelectableSettlers().set(idx, null);
        settler = null;
        Game.getInstance().checkTurnEnd();
        gui.settlerPerformedAction();
    }
    
    public void move() {
    	Asteroid asteroid = Game.getInstance().getSelectedAsteroid();
    	Asteroid currentAsteroid = settler.getAsteroid();
    	if(asteroid != null) {
    		if(asteroid != currentAsteroid) {
    			List<AsteroidField> neighbors = currentAsteroid.getNeighbours();
        		boolean isNeighbour = false;
         	 	for (AsteroidField field : neighbors) {
         	 		if(field.getAsteroids().contains(asteroid)) {
         	 			isNeighbour = true;
         	 			break;
         	 		}
         	 	}
        		if(isNeighbour) {
        			settler.move(asteroid);
        	    	performAction();
        		}
        		else
        			Game.getInstance().log("Are you blind? You cannot go there man... Choose one of your neighbours!");
    		}
    		else
    			Game.getInstance().log("What a lazy man. You standing right there. Choose an other!");
    		
    	}
    	else
    		Game.getInstance().log("Please choose an asteroid... idiot.");
    }
    
    public void drill() {
        settler.drill();
    	performAction();
    }
    
    public void mine() {        
        settler.mine();
    	performAction();
    }

    public void buildTeleportGate() {        
        settler.build(new TeleportGatePair());
    	performAction();
    }

    public void placeTeleportGate() {
        settler.placeTeleportGate();
    	performAction();
    }

    public void buildRobot() {
        Robot r = new Robot();
        settler.build(r);
    	performAction();
    }

    public void putIronBack() {
        settler.putIronBack();
    	performAction();
    }

    public void putCoalBack() {
        settler.putCoalBack();
    	performAction();
    }

    public void putUraniumBack() {
        settler.putUraniumBack();
    	performAction();
    }

    public void putIceBack() {
        settler.putIceBack();
    	performAction();
    }
    
    
    public void setGui(GameFrame gameFrame) {
		gui = gameFrame;
	}

	public Settler getSelectedSettler() {
		return settler;
	}
}
