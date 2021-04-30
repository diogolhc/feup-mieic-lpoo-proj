package controller;

import controller.farm.FarmController;
import gui.GUI;
import model.GameModel;
import viewer.GameViewer;

import java.io.IOException;

public class GameController {
    private GameControllerState gameControllerState;
    private final GameViewer viewer;
    private final GameModel model;

    public GameController(GameViewer viewer, GameModel model) {
        this.viewer = viewer;
        this.model = model;
        this.gameControllerState = new FarmController(viewer, model);
    }

    public void setGameControllerState(GameControllerState state) {
        this.gameControllerState = state;
    }

    public void run() throws IOException {
        while (true) {
            this.viewer.draw(model);

            GUI.ACTION action = viewer.getNextAction();
            if (action == GUI.ACTION.QUIT) break;

            this.gameControllerState.doAction(action);
        }

        this.viewer.closeGUI();
    }
}
