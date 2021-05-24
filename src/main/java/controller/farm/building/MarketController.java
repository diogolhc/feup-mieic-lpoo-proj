package controller.farm.building;

import controller.GameController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import controller.command.OpenPopupMenuCommand;
import controller.command.SetControllerStateCommand;
import controller.farm.FarmController;
import controller.menu.builder.AlertMenuControllerBuilder;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.WarehouseMenuControllerBuilder;
import controller.menu.builder.market.MarketMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.building.House;
import model.farm.building.Market;
import model.farm.building.Warehouse;

public class MarketController extends BuildingController<Market> {
    private final GameController controller;
    private final Farm farm;

    public MarketController(GameController controller, Farm farm) {
        this.controller = controller;
        this.farm = farm;
    }

    @Override
    public Command getInteractionCommand(Market market) {
        if (this.controller.getGameControllerState() instanceof FarmController) {
            FarmController farmController = (FarmController) this.controller.getGameControllerState();
            PopupMenuControllerBuilder menuControllerBuilder = new MarketMenuControllerBuilder(
                    this.controller, this.farm, farmController);
            return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
        } else {
            // This never happens because the interaction command is retrieved after
            // a farmer interaction (which happens in FarmWithFarmerController)
            throw new RuntimeException(
                    "LOGIC ERROR: Open market in invalid state: " + this.controller.getGameControllerState().getClass().toString());
        }
    }

    @Override
    public Command getDemolishCommand(Market market) {
        return new OpenPopupMenuCommand(this.controller, new AlertMenuControllerBuilder(this.controller,
                "MARKET CANNOT BE DEMOLISHED"));
    }
}
