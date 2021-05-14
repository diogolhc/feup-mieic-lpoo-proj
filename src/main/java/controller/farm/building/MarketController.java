package controller.farm.building;

import controller.GameController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import model.farm.building.House;
import model.farm.building.Market;

public class MarketController extends BuildingController<Market> {
    private final GameController controller;

    public MarketController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public Command getInteractionCommand(Market market) {
        // TODO
        System.out.println("Market interaction not implemented");
        return new NoOperationCommand();
    }
}
