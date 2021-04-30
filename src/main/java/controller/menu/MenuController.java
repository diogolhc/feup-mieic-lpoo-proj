package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import gui.GUI;
import model.GameModel;
import model.menu.Button;

public class MenuController implements GameControllerState {
    private GameModel model;
    private GameController controller;

    public MenuController(GameController controller) {
        this.model = controller.getModel();
        this.controller = controller;
        for (Button button: model.getMenu().getButtons()) {
            ButtonController buttonController = new ExperimentalButtonController(controller, button);
            this.controller.getMouseListener().addListener(buttonController);
        }
    }

    @Override
    public void doAction(GUI.ACTION action) {

    }
}
