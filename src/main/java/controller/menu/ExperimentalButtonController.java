package controller.menu;

import controller.GameController;
import controller.farm.FarmController;
import model.GameModel;
import model.Position;
import model.menu.Button;
import viewer.GameViewer;
import viewer.farm.FarmViewer;

// TODO won't be around in the final version
public class ExperimentalButtonController extends ButtonController {
    private final GameViewer viewer;
    private GameController controller;

    public ExperimentalButtonController(GameController controller, Button button) {
        super(button);
        this.controller = controller;
        this.viewer = controller.getViewer();
    }

    @Override
    public void doButtonAction() {
        // TODO mouse can't update screen without key press for now (threads will be needed)
        // for now just press any key at all after clicking somewhere with the mouse
        this.controller.getModel().setMenu(null);
        this.controller.setGameControllerState(new FarmController(this.controller));
        this.viewer.setGameViewerState(new FarmViewer());
    }
}
