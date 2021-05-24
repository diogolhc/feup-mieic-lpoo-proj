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
import model.farm.building.Building;
import model.farm.building.BuildingSet;
import model.farm.building.CropField;
import viewer.GameViewer;
import viewer.farm.FarmNewBuildingViewer;
import viewer.farm.FarmWithFarmerViewer;

public class FarmNewBuildingController extends FarmController {
    private Building newBuilding;

    public FarmNewBuildingController(FarmController farmController, Building newBuilding) {
        super(farmController);
        this.newBuilding = newBuilding;
    }

    @Override
    public void reactKeyboard(GUI.ACTION action) {
        if (action == GUI.ACTION.INTERACT) chooseBuildingPlace();
        NewBuildingController newBuildingController = new NewBuildingController(this.farm, this.newBuilding);
        newBuildingController.doAction(action);
    }

    @Override
    public void reactMouseMovement(Position position) {}

    @Override
    public void reactMouseClick(Position position) {}

    private void chooseBuildingPlace() {
        // TODO
    }

    @Override
    public GameViewer getViewer() {
        return new FarmNewBuildingViewer(this.farm, this.newBuilding);
    }
}
