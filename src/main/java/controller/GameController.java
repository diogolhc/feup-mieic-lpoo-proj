package controller;

import controller.farm.FarmController;
import gui.GUI;
import gui.MouseListener;
import model.Position;
import model.farm.Farm;

import java.io.IOException;

public class GameController implements MouseListener {
    private GameControllerState gameControllerState;
    private GUI gui;

    public GameController(GUI gui) {
        this.gui = gui;
        this.gameControllerState = new FarmController(new Farm(40, 20), this);
        this.gui.setMouseListener(this);
    }

    public void setGameControllerState(GameControllerState state) {
        this.gameControllerState = state;
    }

    public GameControllerState getGameControllerState() {
        return this.gameControllerState;
    }

    public void run() throws IOException {
        while (true) {
            this.gameControllerState.getViewer().drawScreen(gui);

            GUI.ACTION action = this.gui.getNextAction();
            if (action == GUI.ACTION.QUIT) break;

            this.gameControllerState.reactKeyboard(action);
        }

        this.gui.close();
    }

    @Override
    public void onMouseMovement(int x, int y) {
        this.gameControllerState.reactMouseMovement(new Position(x, y));
    }

    @Override
    public void onMouseClick(int x, int y) {
        this.gameControllerState.reactMouseClick(new Position(x, y));
    }
}
