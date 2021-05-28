package controller.farm;

import controller.GameController;
import controller.GameControllerState;
import controller.TimeConverter;
import controller.farm.element.WeatherController;
import controller.farm.element.building.CropFieldController;
import controller.farm.element.building.*;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.crop_field.CropField;
import model.farm.building.stockyard.Stockyard;

public abstract class FarmController implements GameControllerState {
    protected final Farm farm;
    protected final GameController controller;
    private final TimeConverter timeConverter;
    private final WeatherController weatherController;

    public FarmController(FarmController farmController) {
        this.farm = farmController.farm;
        this.controller = farmController.controller;
        this.timeConverter = farmController.timeConverter;
        this.weatherController = farmController.weatherController;
    }

    public FarmController(Farm farm, GameController controller, long realSecToGameMinutesRate) {
        this.farm = farm;
        this.controller = controller;
        this.timeConverter = new TimeConverter(realSecToGameMinutesRate);
        this.weatherController = new WeatherController(this.farm);
    }

    public TimeConverter getTimeConverter() {
        return this.timeConverter;
    }

    @Override
    public void reactMouseMovement(Position position) {}

    @Override
    public void reactMouseClick(Position position) {}

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {
        InGameTime elapsedTime = this.timeConverter.convert(elapsedTimeSinceLastFrame);

        this.farm.setTime(this.farm.getTime().add(elapsedTime));

        CropFieldController cropFieldController = new CropFieldController(this.controller, this.farm);
        for (CropField cropField: this.farm.getBuildings().getCropFields()) {
            cropFieldController.reactTimePassed(cropField, elapsedTime);
        }

        StockyardController stockyardController = new StockyardController(this.controller, this.farm);
        for (Stockyard stockyard: this.farm.getBuildings().getStockyards()) {
            stockyardController.reactTimePassed(stockyard, elapsedTime);
        }

        this.weatherController.reactTimePassed(elapsedTime);
    }

    public Farm getFarm() {
        return this.farm;
    }

    @Override
    public void reactChangeState() {}
}
