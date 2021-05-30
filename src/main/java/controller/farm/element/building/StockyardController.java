package controller.farm.element.building;

import controller.GameController;
import controller.GameControllerState;
import controller.command.*;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.command.controller_state.SetControllerStateCommand;
import controller.farm.FarmDemolishController;
import controller.farm.FarmWithFarmerController;
import controller.farm.element.entity.AnimalController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.building.stockyard.CollectMenuControllerBuilder;
import controller.menu.builder.building.stockyard.FeedAnimalsMenuControllerBuilder;
//import controller.menu.builder.building.stockyard.ProducingMenuControllerBuilder;
import controller.menu.builder.building.stockyard.ProducingMenuControllerBuilder;
import model.InGameTime;
import model.farm.building.stockyard.StockyardAnimals;
import model.farm.entity.Animal;
import model.farm.Farm;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.building.stockyard.state.Producing;
import model.farm.building.stockyard.state.ReadyToCollect;

public class StockyardController extends BuildingController<Stockyard> {
    private final GameController controller;
    private Farm farm;

    public StockyardController(GameController controller, Farm farm) {
        this.controller = controller;
        this.farm = farm;
    }

    @Override
    public Command getInteractionCommand(Stockyard stockyard) {
        PopupMenuControllerBuilder menuControllerBuilder;

        if (stockyard.getState() instanceof NotProducing) {
            menuControllerBuilder = new FeedAnimalsMenuControllerBuilder(this.controller, this.farm, stockyard);
        } else if ( stockyard.getState() instanceof Producing) {
            menuControllerBuilder = new ProducingMenuControllerBuilder(this.controller, this.farm, stockyard);
        } else if (stockyard.getState() instanceof ReadyToCollect) {
            menuControllerBuilder = new CollectMenuControllerBuilder(this.controller, this.farm.getInventory(), stockyard);
        } else {
            // This should never happen
            throw new RuntimeException(
                    "LOGIC ERROR: Unhandled StockyardState: " + stockyard.getState().getClass().toString());
        }

        return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
    }

    @Override
    public Command getDemolishCommand(Stockyard stockyard) {
        GameControllerState gameControllerState = this.controller.getGameControllerState();
        if (gameControllerState instanceof FarmDemolishController) {
            gameControllerState = new FarmWithFarmerController((FarmDemolishController) gameControllerState);
        }

        return new CompoundCommand()
                .addCommand(() -> this.farm.getBuildings().removeStockyard(stockyard))
                .addCommand(new SetControllerStateCommand(this.controller, gameControllerState));
    }

    public void reactTimePassed(Stockyard stockyard, InGameTime elapsedTime) {
        StockyardAnimals animals = stockyard.getAnimals();
        AnimalController animalController = new AnimalController(animals);
        for (Animal animal: animals.getList()) {
            animalController.reactTimePassed(animal, elapsedTime);
        }

        stockyard.setRemainingTime(stockyard.getRemainingTime().subtract(elapsedTime));
        stockyard.changeCollectAmount(this.farm.getCurrentWeather().getEffect(elapsedTime));
    }
}
