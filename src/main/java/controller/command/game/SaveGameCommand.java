package controller.command.game;

import controller.GameController;
import controller.command.Command;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.farm.Farm;
import java.io.*;

public class SaveGameCommand implements Command {
    private final GameController gameController;
    private final Farm farm;
    private final String saveFileName;

    public SaveGameCommand(GameController gameController, Farm farm, String saveFileName) {
        this.gameController = gameController;
        this.farm = farm;
        this.saveFileName = saveFileName;
    }

    @Override
    public void execute() {
        try {
            FileOutputStream out = new FileOutputStream(this.saveFileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(this.farm);

            new OpenPopupMenuCommand(this.gameController, new AlertMenuControllerBuilder(
                    this.gameController, "GAME SAVED SUCCESSFULLY")).execute();
        } catch (IOException e) {
            new OpenPopupMenuCommand(this.gameController, new AlertMenuControllerBuilder(
                    this.gameController, "FAILED TO SAVE GAME")).execute();
            e.printStackTrace();
        }
    }
}
