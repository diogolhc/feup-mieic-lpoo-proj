package controller.farm;

import controller.GameController;
import controller.GameControllerState;
import controller.RealTimeToInGameTimeConverter;
import controller.farm.building.*;
import gui.GUI;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.BuildingSet;
import model.farm.building.Stockyard;
import model.farm.building.crop_field.CropField;
import viewer.GameViewer;
import viewer.farm.FarmViewer;

public class FarmController implements GameControllerState {
    private Farm farm;
    private GameController controller;
    private RealTimeToInGameTimeConverter realTimeToInGameTimeConverter;
    WeatherController weatherController;

    public FarmController(Farm farm, GameController controller, double realSecToGameMinutesRate) {
        this.farm = farm;
        this.controller = controller;
        this.realTimeToInGameTimeConverter = new RealTimeToInGameTimeConverter(realSecToGameMinutesRate);
        this.weatherController = new WeatherController(this.farm); // TODO should this be done in a different way?
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

        CropFieldController cropFieldController = new CropFieldController(this.controller, this.farm.getCrops());
        for (CropField cropField: farmBuildings.getCropFields()) {
            cropFieldController.reactInteraction(cropField, farmerPosition);
        }

        StockyardController stockyardController = new StockyardController(this.controller);
        for (Stockyard stockyard : farmBuildings.getStockyards()) {
            stockyardController.reactInteraction(stockyard, farmerPosition);
        }

        HouseController houseController = new HouseController(this.controller);
        houseController.reactInteraction(farmBuildings.getHouse(), farmerPosition);

        MarketController marketController = new MarketController(this.controller);
        marketController.reactInteraction(farmBuildings.getMarket(), farmerPosition);

        WarehouseController warehouseController = new WarehouseController(this.controller);
        warehouseController.reactInteraction(farmBuildings.getWarehouse(), farmerPosition);

    }

    @Override
    public void reactMouseMovement(Position position) {}

    @Override
    public void reactMouseClick(Position position) {}

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {
        InGameTime elapsedTime = this.realTimeToInGameTimeConverter.convert(elapsedTimeSinceLastFrame);

        this.farm.setTime(this.farm.getTime().add(elapsedTime));

        CropFieldController cropFieldController = new CropFieldController(this.controller, this.farm.getCrops());
        for (CropField cropField : this.farm.getBuildings().getCropFields()) {
            cropFieldController.reactTimePassed(cropField, elapsedTime);
        }

        this.weatherController.reactTimePassed(elapsedTime);
    }

    @Override
    public GameViewer getViewer() {
        return new FarmViewer(this.farm);
    }
}
