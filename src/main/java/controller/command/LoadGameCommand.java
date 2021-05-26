package controller.command;

import controller.GameController;
import controller.farm.FarmWithFarmerController;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.farm.Farm;

import java.io.*;

public class LoadGameCommand implements Command {
    private final GameController gameController;
    private final String saveFileName;

    public LoadGameCommand(GameController gameController, String saveFileName) {
        this.gameController = gameController;
        this.saveFileName = saveFileName;
    }

    @Override
    public void execute() {
        try {
            FileInputStream in = new FileInputStream(this.saveFileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            Farm farm = (Farm) objectInputStream.readObject();

            this.gameController.setGameControllerState(new FarmWithFarmerController(farm, this.gameController, 1));
        } catch (FileNotFoundException e) {
            new OpenPopupMenuCommand(this.gameController, new AlertMenuControllerBuilder(
                    this.gameController, "NO SAVE FILE FOUND")).execute();
        } catch (IOException | ClassNotFoundException e) {
            new OpenPopupMenuCommand(this.gameController, new AlertMenuControllerBuilder(
                    this.gameController, "FAILED TO LOAD SAVE FILE")).execute();
            e.printStackTrace();
        }
    }
}
