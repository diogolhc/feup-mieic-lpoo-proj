package controller.farm.element.building.edifice;

import controller.GameController;
import controller.command.Command;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.menu.builder.*;
import controller.menu.builder.building.WarehouseMenuControllerBuilder;
import model.farm.Farm;
import model.farm.building.Edifice;

public class WarehouseController extends EdificeController {
    private Farm farm;

    public WarehouseController(GameController controller, Farm farm) {
        super(controller);
        this.farm = farm;
    }

    @Override
    public Command getInteractionCommand(Edifice warehouse) {
        PopupMenuControllerBuilder menuControllerBuilder = new WarehouseMenuControllerBuilder(
                this.controller, farm, warehouse);
        return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
    }
}
