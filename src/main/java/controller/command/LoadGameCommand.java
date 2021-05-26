package controller.command;

import controller.GameController;
import controller.farm.FarmWithFarmerController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.farm.Farm;

import java.io.*;

public class LoadGameCommand implements Command {
    private final GameController gameController;

    public LoadGameCommand(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void execute() {
        try {
            FileInputStream in = new FileInputStream("save");
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            Farm farm = (Farm) objectInputStream.readObject();

            this.gameController.setGameControllerState(new FarmWithFarmerController(farm, this.gameController, 1));

        } catch (IOException | ClassNotFoundException e) {
            PopupMenuControllerBuilder alert = new AlertMenuControllerBuilder(this.gameController,
                    "FAILED TO LOAD GAME\nSAVE FILE MIGHT BE MISSING");
            new OpenPopupMenuCommand(this.gameController, alert).execute();
        }
    }

}
