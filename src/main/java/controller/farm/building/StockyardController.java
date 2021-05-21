package controller.farm.building;

import controller.GameController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import model.InGameTime;
import model.farm.building.Stockyard;
import model.farm.building.crop_field.CropField;

public class StockyardController extends BuildingController<Stockyard> {

    private final GameController controller;

    public StockyardController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public Command getInteractionCommand(Stockyard stockyard) {
        // TODO
        System.out.println("Stockyard interaction not implemented yet");
        return new NoOperationCommand();
    }

    public void reactTimePassed(Stockyard stockyard) {
        // TODO Make animals of stockyard move and increse its Hunger
    }
}
