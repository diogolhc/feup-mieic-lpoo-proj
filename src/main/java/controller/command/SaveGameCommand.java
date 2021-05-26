package controller.command;

import controller.GameController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.farm.Farm;
import java.io.*;


public class SaveGameCommand implements Command {
    private final GameController gameController;
    private final Farm farm;

    public SaveGameCommand(GameController gameController, Farm farm) {
        this.gameController = gameController;
        this.farm = farm;
    }

    @Override
    public void execute() {
        try {
            FileOutputStream out = new FileOutputStream("save");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(this.farm);

        } catch (IOException e) {
            PopupMenuControllerBuilder alert = new AlertMenuControllerBuilder(this.gameController,
                    "FAILED TO SAVE GAME\nSAVE FILE MIGHT BE CORRUPTED");
            new OpenPopupMenuCommand(this.gameController, alert).execute();
        }
    }
}
