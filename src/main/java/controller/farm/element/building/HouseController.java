package controller.farm.element.building;

import controller.GameController;
import controller.command.Command;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.farm.FarmController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.HouseMenuControllerBuilder;
import model.farm.building.Edifice;

public class HouseController extends EdificeController {
    public static final int SLEEP_RATE_MULTIPLIER = 15;

    public HouseController(GameController controller) {
        super(controller);
    }

    @Override
    public Command getInteractionCommand(Edifice house) {
        if (this.controller.getGameControllerState() instanceof FarmController) {
            FarmController farmController = (FarmController) this.controller.getGameControllerState();
            PopupMenuControllerBuilder menuControllerBuilder = new HouseMenuControllerBuilder(
                    this.controller, farmController, house, SLEEP_RATE_MULTIPLIER);
            return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
        } else {
            // This never happens because the interaction command is retrieved after
            // a farmer interaction (which happens in FarmWithFarmerController)
            throw new RuntimeException(
                    "LOGIC ERROR: Open market in invalid state: " + this.controller.getGameControllerState().getClass().toString());
        }
    }
}
