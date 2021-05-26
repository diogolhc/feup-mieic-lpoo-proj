package controller.command;

import controller.GameController;
import controller.farm.FarmWithFarmerController;
import model.farm.Farm;

public class NewGameCommand implements Command {
    private final GameController gameController;
    private final Farm farm;

    public NewGameCommand(GameController gameController, Farm farm) {
        this.gameController = gameController;
        this.farm = farm;
    }

    @Override
    public void execute() {
        this.gameController.setGameControllerState(new FarmWithFarmerController(farm, this.gameController, 1));
    }

}
