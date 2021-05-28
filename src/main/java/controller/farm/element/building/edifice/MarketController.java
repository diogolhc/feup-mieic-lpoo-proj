package controller.farm.element.building.edifice;

import controller.GameController;
import controller.command.Command;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.farm.FarmController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.building.market.MarketMenuControllerBuilder;
import model.farm.Farm;
import model.farm.building.Edifice;

public class MarketController extends EdificeController {
    private final Farm farm;

    public MarketController(GameController controller, Farm farm) {
        super(controller);
        this.farm = farm;
    }

    @Override
    public Command getInteractionCommand(Edifice market) {
        if (this.controller.getGameControllerState() instanceof FarmController) {
            FarmController farmController = (FarmController) this.controller.getGameControllerState();

            PopupMenuControllerBuilder menuControllerBuilder = new MarketMenuControllerBuilder(this.controller, farmController);
            return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
        } else {
            // This never happens because the interaction command is retrieved after
            // a farmer interaction (which happens in FarmWithFarmerController)
            throw new RuntimeException(
                    "LOGIC ERROR: Open market in invalid state: " + this.controller.getGameControllerState().getClass().toString());
        }
    }
}
