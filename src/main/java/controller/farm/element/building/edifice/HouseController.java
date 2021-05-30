package controller.farm.element.building.edifice;

import controller.GameController;
import controller.command.Command;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.farm.FarmController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.building.HouseMenuControllerBuilder;
import model.farm.building.Edifice;

public class HouseController extends EdificeController {
    public static final int SLEEP_RATE_MULTIPLIER = 15;
    private FarmController farmController;

    public HouseController(GameController controller, FarmController farmController) {
        super(controller);
        this.farmController = farmController;
    }

    @Override
    public Command getInteractionCommand(Edifice house) {
        PopupMenuControllerBuilder menuControllerBuilder = new HouseMenuControllerBuilder(
                this.controller, this.farmController, house, SLEEP_RATE_MULTIPLIER);
        return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
    }
}
