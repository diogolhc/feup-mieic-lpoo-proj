package controller.farm;

import gui.GUI;
import viewer.GameViewer;
import viewer.farm.FarmRestingViewer;

public class FarmRestingController extends FarmController {
    public FarmRestingController(FarmController farmController) {
        super(farmController);
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
