package controller.farm.building;

import controller.GameController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import controller.command.OpenPopupMenuCommand;
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
        PopupMenuControllerBuilder menuControllerBuilder = new WarehouseMenuControllerBuilder(this.controller, farm);
        return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
    }

    @Override
    public Command getDemolishCommand(Warehouse warehouse) {
        return new OpenPopupMenuCommand(this.controller, new AlertMenuControllerBuilder(this.controller,
                "WAREHOUSE CANNOT BE DEMOLISHED"));
    }
}
