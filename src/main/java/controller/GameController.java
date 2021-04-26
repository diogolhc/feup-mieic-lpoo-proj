package controller;

import gui.GUI;
import model.Farm;
import model.GameModel;
import viewer.GameViewer;

import java.io.IOException;

public class GameController {
    private final FarmController farmController;
    private GameViewer viewer;
    private GameModel model;

    public GameController(GameViewer viewer, GameModel model) {
        this.viewer = viewer;
        this.model = model;
        this.farmController = new FarmController(model.getFarm());
    }

    public void run() throws IOException {
        while (true) {
            viewer.drawFarm(model.getFarm());

            GUI.ACTION action = viewer.getNextAction();
            if (action == GUI.ACTION.QUIT) break;

            farmController.doAction(action);
        }

        viewer.closeGUI();
    }
}
