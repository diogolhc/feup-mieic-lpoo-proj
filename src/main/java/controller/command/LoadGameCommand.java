package controller.command;

import controller.GameController;
import controller.farm.FarmWithFarmerController;
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
            FileInputStream in = new FileInputStream("save"); // TODO this is used both in load as in save...
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            Farm farm = (Farm) objectInputStream.readObject();

            this.gameController.setGameControllerState(new FarmWithFarmerController(farm, this.gameController, 1));
        } catch (Exception e) {
            // TODO should some message be printed without terminating the game since the user can use new game option?
        }

    }

}
