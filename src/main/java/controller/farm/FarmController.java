package controller.farm;

import controller.GameController;
import controller.GameControllerState;
import controller.RealTimeToInGameTimeConverter;
import controller.farm.building.CropFieldController;
import controller.farm.building.*;
import model.InGameTime;
import model.farm.Farm;
import model.farm.building.CropField;
import model.farm.building.Stockyard;

public abstract class FarmController implements GameControllerState {
    protected Farm farm;
    protected GameController controller;
    protected RealTimeToInGameTimeConverter realTimeToInGameTimeConverter;
    protected WeatherController weatherController;

    public FarmController(FarmController farmController) {
        this.farm = farmController.farm;
        this.controller = farmController.controller;
        this.realTimeToInGameTimeConverter = farmController.realTimeToInGameTimeConverter;
        this.weatherController = farmController.weatherController;
    }

    public FarmController(Farm farm, GameController controller, long realSecToGameMinutesRate) {
        this.farm = farm;
        this.controller = controller;
        this.realTimeToInGameTimeConverter = new RealTimeToInGameTimeConverter(realSecToGameMinutesRate);
        this.weatherController = new WeatherController(this.farm); // TODO should this be done in a different way?
    }

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {
        InGameTime elapsedTime = this.realTimeToInGameTimeConverter.convert(elapsedTimeSinceLastFrame);

        this.farm.setTime(this.farm.getTime().add(elapsedTime));

        CropFieldController cropFieldController = new CropFieldController(this.controller, this.farm);
        for (CropField cropField : this.farm.getBuildings().getCropFields()) {
            cropFieldController.reactTimePassed(cropField, elapsedTime);
        }

        StockyardController stockyardController = new StockyardController(this.controller, this.farm);

        for (Stockyard stockyard : this.farm.getBuildings().getStockyards()) {
            stockyardController.reactTimePassed(stockyard, elapsedTime);
        }

        stockyardController.resetLastMovement();


        this.weatherController.reactTimePassed(elapsedTime);
    }

    public Farm getFarm() {
        return this.farm;
    }

    @Override
    public void reactChangeState() {}

}
