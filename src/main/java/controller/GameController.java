package controller;

import controller.farm.FarmController;
import gui.GUI;
import model.AtmosphericTime;
import model.GameModel;
import viewer.GameViewer;

import java.io.IOException;

public class GameController {
    private final MouseListener mouseListener;
    private GameControllerState gameControllerState;
    private ChronologicalTimeController chronologicalTimeController;
    private AtmosphericTimeController atmosphericTimeController;
    private final GameViewer viewer;
    private final GameModel model;

    public GameController(GameViewer viewer, GameModel model) {
        this.viewer = viewer;
        this.model = model;
        this.gameControllerState = new FarmController(this);
        this.mouseListener = new MouseListener();
        this.chronologicalTimeController = new ChronologicalTimeController(1);
        this.atmosphericTimeController = new AtmosphericTimeController();
        viewer.setMouseListener(this.mouseListener);
    }

    public void setGameControllerState(GameControllerState state) {
        this.gameControllerState = state;
    }

    public GameControllerState getGameControllerState() {
        return gameControllerState;
    }

    public MouseListener getMouseListener() {
        return this.mouseListener;
    }

    public GameModel getModel() {
        return this.model;
    }

    public void run() throws IOException {
        while (true) {
            this.viewer.draw(model);

            GUI.ACTION action = viewer.getNextAction();
            if (action == GUI.ACTION.QUIT) break;

            this.gameControllerState.doAction(action);
            this.chronologicalTimeController.advanceTime(this.model.getChronologicalTime());
            this.atmosphericTimeController.updateTime(this.model.getAtmosphericTime(), this.model.getChronologicalTime().getDay());
        }

        this.viewer.closeGUI();
    }

    public GameViewer getViewer() {
        return this.viewer;
    }
}
