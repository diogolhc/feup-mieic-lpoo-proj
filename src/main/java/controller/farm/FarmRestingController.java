package controller.farm;

import controller.GameController;
import gui.GUI;
import model.Position;
import model.farm.Farm;
import viewer.GameViewer;
import viewer.farm.FarmRestingViewer;

public class FarmRestingController extends FarmController {
    public FarmRestingController(FarmController farmController) {
        super(farmController);
    }

    public FarmRestingController(Farm farm, GameController controller, long realSecToGameMinutesRate) {
        super(farm, controller, realSecToGameMinutesRate);
    }

    @Override
    public void reactKeyboard(GUI.KEYBOARD_ACTION action) {
        if (action != GUI.KEYBOARD_ACTION.NONE) returnToFarmerController();
    }

    @Override
    public void reactMouseMovement(Position position) {}

    @Override
    public void reactMouseClick(Position position) {}

    private void returnToFarmerController() {
        this.timeConverter.setRateMultiplier(1);
        this.controller.setGameControllerState(new FarmWithFarmerController(this));
    }

    @Override
    public GameViewer getViewer() {
        return new FarmRestingViewer(this.farm);
    }
}
