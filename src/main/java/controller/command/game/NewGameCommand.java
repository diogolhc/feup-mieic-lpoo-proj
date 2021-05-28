package controller.command.game;

import controller.GameController;
import controller.command.Command;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.farm.FarmWithFarmerController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.farm.Farm;
import model.farm.builder.NewGameFarmBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public class NewGameCommand implements Command {
    private final GameController gameController;

    public NewGameCommand(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void execute() {
        try {
            Farm farm = new NewGameFarmBuilder().buildFarm();
            this.gameController.setGameControllerState(
                    new FarmWithFarmerController(farm, this.gameController, 1));
        } catch (IOException | URISyntaxException e) {
            PopupMenuControllerBuilder alert = new AlertMenuControllerBuilder(this.gameController,
                    "FAILED TO START NEW GAME\nDATA FILES MIGHT BE CORRUPTED");
            new OpenPopupMenuCommand(this.gameController, alert).execute();
        }
    }
}
