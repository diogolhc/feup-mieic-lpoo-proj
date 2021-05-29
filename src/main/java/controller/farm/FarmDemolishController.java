package controller.farm;

import controller.GameController;
import controller.farm.element.building.*;
import controller.farm.element.building.edifice.HouseController;
import controller.farm.element.building.edifice.MarketController;
import controller.farm.element.building.edifice.WarehouseController;
import controller.farm.element.entity.keyboard_reactor.DemolishMarkerController;
import gui.GUI;
import model.Position;
import model.farm.Farm;
import model.farm.entity.Entity;
import model.farm.building.BuildingSet;
import model.farm.building.crop_field.CropField;
import model.farm.building.stockyard.Stockyard;
import viewer.GameViewer;
import viewer.farm.FarmDemolishViewer;

import java.util.ArrayList;
import java.util.List;

public class FarmDemolishController extends FarmController {
    private final Entity demolishMarker;

    public FarmDemolishController(FarmController farmController) {
        super(farmController);
        this.demolishMarker = new Entity(new Position(1, 1));
    }

    public FarmDemolishController(Farm farm, GameController controller, long realSecToGameMinutesRate, Entity marker) {
        super(farm, controller, realSecToGameMinutesRate);
        this.demolishMarker = marker;
    }

    @Override
    public void reactKeyboard(GUI.KEYBOARD_ACTION action) {
        if (action == GUI.KEYBOARD_ACTION.BACK) this.returnToFarmerController();
        if (action == GUI.KEYBOARD_ACTION.INTERACT) this.reactDemolish();
        DemolishMarkerController demolishMarkerController = new DemolishMarkerController(this.farm, this.demolishMarker);
        demolishMarkerController.reactKeyboard(action);
    }

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

        HouseController houseController = new HouseController(this.controller);
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
