package controller.farm.building;

import controller.GameController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import controller.command.SetControllerStateCommand;
import controller.menu.builder.*;
import model.Position;
import model.farm.Farm;
import model.farm.building.Market;
import model.farm.building.Warehouse;
import model.farm.building.crop_field_state.NotPlanted;
import model.farm.building.crop_field_state.Planted;
import model.farm.building.crop_field_state.ReadyToHarvest;

public class WarehouseController extends BuildingController<Warehouse> {
    private final GameController controller;
    private Farm farm;

    public WarehouseController(GameController controller, Farm farm) {
        this.controller = controller;
        this.farm = farm;
    }

    @Override
    public Command getInteractionCommand(Warehouse warehouse) {
        MenuControllerBuilder menuControllerBuilder = new WarehouseMenuControllerBuilder(this.controller, farm);
        return new SetControllerStateCommand(this.controller, menuControllerBuilder.buildMenu(new Position(1, 1)));
    }
}
