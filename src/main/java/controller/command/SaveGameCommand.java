package controller.command;

import controller.GameController;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.farm.Farm;
import java.io.*;


public class SaveGameCommand implements Command {
    private GameController gameController;
    private final Farm farm;
    private String saveFileName;

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
