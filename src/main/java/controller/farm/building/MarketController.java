package controller.farm.building;

import controller.GameController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import controller.command.SetControllerStateCommand;
import controller.menu.builder.MenuControllerBuilder;
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
        MenuControllerBuilder menuControllerBuilder = new MarketMenuControllerBuilder(this.controller, farm);
        return new SetControllerStateCommand(this.controller, menuControllerBuilder.buildMenu(new Position(1, 1)));
    }
}
