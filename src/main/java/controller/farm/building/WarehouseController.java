package controller.farm.building;

import controller.GameController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import model.farm.building.Market;
import model.farm.building.Warehouse;

public class WarehouseController extends BuildingController<Warehouse> {
    private final GameController controller;

    public WarehouseController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public Command getInteractionCommand(Warehouse warehouse) {
        // TODO
        System.out.println("Warehouse interaction not implemented");
        return new NoOperationCommand();
    }
}
