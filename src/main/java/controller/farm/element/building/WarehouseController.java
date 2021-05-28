package controller.farm.element.building;

import controller.GameController;
import controller.command.Command;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.menu.builder.*;
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
        PopupMenuControllerBuilder menuControllerBuilder = new WarehouseMenuControllerBuilder(this.controller, farm);
        return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
    }
}
