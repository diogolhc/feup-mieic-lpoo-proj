package controller.farm;

import controller.farm.building.CropFieldController;
import controller.farm.building.HouseController;
import controller.farm.building.MarketController;
import controller.farm.building.WarehouseController;
import controller.menu.builder.AlertMenuControllerBuilder;
import gui.GUI;
import model.Position;
import model.farm.Currency;
import model.farm.DemolishMarker;
import model.farm.building.Building;
import model.farm.building.BuildingSet;
import model.farm.building.CropField;
import viewer.GameViewer;
import viewer.farm.FarmDemolishViewer;
import viewer.farm.FarmNewBuildingViewer;

import java.util.ArrayList;
import java.util.List;

public class FarmDemolishController extends FarmController {
    private DemolishMarker demolishMarker;

    public FarmDemolishController(FarmController farmController) {
        super(farmController);
        this.demolishMarker = new DemolishMarker(new Position(1, 1));
    }

    @Override
    public void reactKeyboard(GUI.ACTION action) {
        if (action == GUI.ACTION.BACK) returnToFarmerController();
        if (action == GUI.ACTION.INTERACT) reactDemolish();
        DemolishMarkerController demolishMarkerController = new DemolishMarkerController(this.farm, this.demolishMarker);
        demolishMarkerController.doAction(action);
    }

    @Override
    public void reactMouseMovement(Position position) {}

    @Override
    public void reactMouseClick(Position position) {}

    private void returnToFarmerController() {
        this.controller.setGameControllerState(new FarmWithFarmerController(this));
    }

    private void reactDemolish() {
        Position demolishPosition = this.demolishMarker.getPosition();
        BuildingSet farmBuildings = this.farm.getBuildings();

        CropFieldController cropFieldController = new CropFieldController(this.controller, this.farm);
        List<CropField> cropFields = new ArrayList<>(farmBuildings.getCropFields());
        for (CropField cropField: cropFields) {
            cropFieldController.reactDemolish(cropField, demolishPosition);
        }

        HouseController houseController = new HouseController(this.controller, this.realTimeToInGameTimeConverter);
        houseController.reactDemolish(farmBuildings.getHouse(), demolishPosition);

        MarketController marketController = new MarketController(this.controller, this.farm);
        marketController.reactDemolish(farmBuildings.getMarket(), demolishPosition);

        WarehouseController warehouseController = new WarehouseController(this.controller, this.farm);
        warehouseController.reactDemolish(farmBuildings.getWarehouse(), demolishPosition);
    }

    @Override
    public GameViewer getViewer() {
        return new FarmDemolishViewer(this.farm, this.demolishMarker);
    }
}
