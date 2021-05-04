package controllers;

import java.util.ArrayList;
import java.util.List;

import models.playfield.Asteroid;
import models.playfield.AsteroidField;
import models.settler.Settler;
import views.GameFrame;
import views.SpaceExplorersGui;

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
        gui.settlerPerformedAction();
    }

    public void mine() {
        /* TODO SelectedSettler.mine */
        
        // settler.mine();
    	performAction();
    }

    public void drill() {
        /* TODO SelectedSettler.drill */
        
        // settler.drill();
    	performAction();
    }

    public void buildTeleportGate() {
        /* TODO SelectedSettler.buildTeleportGate */
        
        // settler.build(new TeleportGatePair());
    	performAction();
    }

    public void placeTeleportGate() {
        /* TODO SelectedSettler.placeTeleportGate */
    	performAction();
    }

    public void buildRobot() {
        /* TODO SelectedSettler.buildRobot */
        
        // Robot r = new Robot(settler.getAsteroid());
        // settler.build(r);
    	performAction();
    }

    public void move() {
        /* TODO SelectedSettler.move */

        // settler.move(Game.getInstance().getSelectedAsteroid())
    	performAction();
    }

    public void putIronBack() {
        /* TODO SelectedSettler.putIronBack */

        // settler.putIronBack();
    	performAction();
    }

    public void putCoalBack() {
        /* TODO SelectedSettler.putCoalBack */

        // settler.putCoalBack();
    	performAction();
    }

    public void putUraniumBack() {
        /* TODO SelectedSettler.move */

        // settler.putUraniumBack();
    	performAction();
    }

    public void putIceBack() {
        /* TODO SelectedSettler.putIceBack */

        // settler.putIceBack();
    	performAction();
    }
    
    
    public void setGui(GameFrame gameFrame) {
		gui = gameFrame;
	}

	public Settler getSelectedSettler() {
		return settler;
	}
}
