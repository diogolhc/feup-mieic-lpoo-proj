package controller.farm;

import controller.farm.element.building.*;
import controller.farm.element.entity.DemolishMarkerController;
import gui.GUI;
import model.Position;
import model.farm.entity.Entity;
import model.farm.building.BuildingSet;
import model.farm.building.CropField;
import model.farm.building.Stockyard;
import viewer.GameViewer;
import viewer.farm.FarmDemolishViewer;

import java.util.ArrayList;
import java.util.List;

public class FarmDemolishController extends FarmController {
    private Entity demolishMarker;

    public FarmDemolishController(FarmController farmController) {
        super(farmController);
        this.demolishMarker = new Entity(new Position(1, 1));
    }

    @Override
    public void reactKeyboard(GUI.KEYBOARD_ACTION action) {
        if (action == GUI.KEYBOARD_ACTION.BACK) returnToFarmerController();
        if (action == GUI.KEYBOARD_ACTION.INTERACT) reactDemolish();
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

        StockyardController stockyardController = new StockyardController(this.controller, this.farm);
        List<Stockyard> stockyards = new ArrayList<>(farmBuildings.getStockyards());
        for (Stockyard stockyard: stockyards) {
            stockyardController.reactDemolish(stockyard, demolishPosition);
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
