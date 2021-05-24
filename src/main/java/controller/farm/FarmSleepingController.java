package controller.farm;

import controller.GameController;
import controller.farm.building.CropFieldController;
import controller.farm.building.HouseController;
import controller.farm.building.MarketController;
import controller.farm.building.WarehouseController;
import gui.GUI;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.BuildingSet;
import model.farm.building.CropField;
import viewer.GameViewer;
import viewer.farm.FarmViewer;
import viewer.farm.FarmWithFarmerViewer;

public class FarmSleepingController extends FarmController {
    public FarmSleepingController(FarmController farmController) {
        super(farmController);
    }

    public FarmSleepingController(Farm farm, GameController controller, long realSecToGameMinutesRate) {
        super(farm, controller, realSecToGameMinutesRate);
    }

    @Override
    public void reactKeyboard(GUI.ACTION action) {
        if (action != GUI.ACTION.NONE) returnToFarmerController();
    }

    @Override
    public void reactMouseMovement(Position position) {}

    @Override
    public void reactMouseClick(Position position) {}

    private void returnToFarmerController() {
        this.realTimeToInGameTimeConverter.setRateMultiplier(1);
        this.controller.setGameControllerState(new FarmWithFarmerController(this));
    }

    @Override
    public GameViewer getViewer() {
        return new FarmViewer(this.farm);
    }
}
