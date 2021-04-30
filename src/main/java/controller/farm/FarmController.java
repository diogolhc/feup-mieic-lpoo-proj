package controller.farm;

import controller.GameControllerState;
import gui.GUI;
import model.GameModel;

public class FarmController implements GameControllerState {
    public FarmController() {}

    @Override
    public void doAction(GameModel model, GUI.ACTION action) {
        FarmerController farmerController = new FarmerController(model.getFarm());
        farmerController.doAction(action);
    }
}
