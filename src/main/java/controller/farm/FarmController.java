package controller.farm;

import controller.GameController;
import controller.GameControllerState;
import controller.farm.building.CropFieldController;
import controller.farm.building.HouseController;
import gui.GUI;
import model.Position;
import model.farm.Farm;
import model.farm.building.BuildingSet;
import model.farm.building.crop_field.CropField;
import viewer.GameViewer;
import viewer.farm.FarmViewer;

public class FarmController implements GameControllerState {
    private Farm farm;
    private GameController controller;

    public FarmController(Farm farm, GameController controller) {
        this.farm = farm;
        this.controller = controller;
    }

    @Override
    public void reactKeyboard(GUI.ACTION action) {
        if (action == GUI.ACTION.INTERACT) reactInteraction();
        FarmerController farmerController = new FarmerController(this.farm);
        farmerController.doAction(action);
    }

    private void reactInteraction() {
        Position farmerPosition = this.farm.getFarmer().getPosition();
        BuildingSet farmBuildings = this.farm.getBuildings();

        CropFieldController cropFieldController = new CropFieldController(this.controller);
        for (CropField cropField: farmBuildings.getCropFields()) {
            cropFieldController.reactInteraction(cropField, farmerPosition);
        }

        HouseController houseController = new HouseController(this.controller);
        houseController.reactInteraction(farmBuildings.getHouse(), farmerPosition);
    }

    @Override
    public void reactMouseMovement(Position position) {
        // TODO
    }

    @Override
    public void reactMouseClick(Position position) {
        // TODO
    }

    @Override
    public GameViewer getViewer() {
        return new FarmViewer(this.farm);
    }
}
