package controller.farm;

import controller.GameController;
import controller.GameControllerState;
import controller.RealTimeToInGameTimeConverter;
import controller.farm.building.CropFieldController;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.CropField;

// TODO rewrite inheritance as composition?
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

        this.weatherController.reactTimePassed(elapsedTime);
    }
}
