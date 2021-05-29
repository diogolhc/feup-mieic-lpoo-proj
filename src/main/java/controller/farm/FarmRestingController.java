package controller.farm;

import controller.GameController;
import gui.GUI;
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

    private void returnToFarmerController() {
        this.getTimeConverter().setRateMultiplier(1);
        this.controller.setGameControllerState(new FarmWithFarmerController(this));
    }

    @Override
    public GameViewer getViewer() {
        return new FarmRestingViewer(this.farm);
    }
}
