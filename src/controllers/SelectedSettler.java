package controllers;

import models.settler.Settler;
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

    public void performAction(String action) {
        /* TODO SelectedSettler.performAction */
    }

    public void mine() {
        /* TODO SelectedSettler.mine */
        
        // settler.mine();
    }

    public void drill() {
        /* TODO SelectedSettler.drill */
        
        // settler.drill();
    }

    public void buildTeleportGate() {
        /* TODO SelectedSettler.buildTeleportGate */
        
        // settler.build(new TeleportGatePair());
    }

    public void placeTeleportGate() {
        /* TODO SelectedSettler.placeTeleportGate */
    }

    public void buildRobot() {
        /* TODO SelectedSettler.buildRobot */
        
        // Robot r = new Robot(settler.getAsteroid());
        // settler.build(r);
    }

    public void move() {
        /* TODO SelectedSettler.move */

        // settler.move(Game.getInstance().getSelectedAsteroid())
    }

    public void putIronBack() {
        /* TODO SelectedSettler.putIronBack */

        // settler.putIronBack();
    }

    public void putCoalBack() {
        /* TODO SelectedSettler.putCoalBack */

        // settler.putCoalBack();
    }

    public void putUraniumBack() {
        /* TODO SelectedSettler.move */

        // settler.putUraniumBack();
    }

    public void putIceBack() {
        /* TODO SelectedSettler.putIceBack */

        // settler.putIceBack();
    }
}
