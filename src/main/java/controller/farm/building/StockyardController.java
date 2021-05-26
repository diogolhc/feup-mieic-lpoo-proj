package controller.farm.building;

import controller.GameController;
import controller.GameControllerState;
import controller.command.*;
import controller.farm.FarmDemolishController;
import controller.farm.FarmWithFarmerController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import controller.farm.AnimalController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.stockyard.CollectMenuControllerBuilder;
import controller.menu.builder.stockyard.FeedAnimalsMenuControllerBuilder;
//import controller.menu.builder.stockyard.ProducingMenuControllerBuilder;
import controller.menu.builder.stockyard.ProducingMenuControllerBuilder;
import model.InGameTime;
import model.farm.Farm;
import model.farm.Animal;
import model.farm.building.Stockyard;
import model.farm.building.stockyard_state.NotProducing;
import model.farm.building.stockyard_state.Producing;
import model.farm.building.stockyard_state.ReadyToCollect;

public class StockyardController extends BuildingController<Stockyard> {
    private final GameController controller;
    private final AnimalController animalController;
    private Farm farm;

    public StockyardController(GameController controller, Farm farm) {
        this.controller = controller;
        this.animalController = new AnimalController();
        this.farm = farm;
    }

    public void resetLastMovement() {
        animalController.reset();
    }

    @Override
    public Command getInteractionCommand(Stockyard stockyard) {
        PopupMenuControllerBuilder menuControllerBuilder;

        if (stockyard.getState() instanceof NotProducing) {
            menuControllerBuilder = new FeedAnimalsMenuControllerBuilder(this.controller, stockyard, stockyard.getLivestockType().getFoodCrop());
        } else if ( stockyard.getState() instanceof Producing) {
            menuControllerBuilder = new ProducingMenuControllerBuilder(this.controller, farm, stockyard);
        } else if (stockyard.getState() instanceof ReadyToCollect) {
            menuControllerBuilder = new CollectMenuControllerBuilder(this.controller, farm.getInventory(), stockyard);
        } else {
            // This should never happen
            throw new RuntimeException(
                    "LOGIC ERROR: Unhandled CropFieldState: " + stockyard.getState().getClass().toString());
        }

        // TODO
        System.out.println(stockyard.getState());
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
        animalController.reactTimePassed(stockyard, elapsedTime);

    }
}
