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
    private final FarmController farmController;

    public MarketController(GameController controller, FarmController farmController) {
        super(controller);
        this.farmController = farmController;
    }

    @Override
    public Command getInteractionCommand(Edifice market) {
        PopupMenuControllerBuilder menuControllerBuilder = new MarketMenuControllerBuilder(
                this.controller, this.farmController, market);
        return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
    }
}
