package controller.farm;

import controller.GameController;
import controller.GameControllerState;
import controller.time.RealTimeToInGameTimeConverter;
import controller.time.oper.InGameTimeOper;
import controller.time.oper.InGameTimeSum;
import controller.farm.weather.SunnyController;
import controller.farm.weather.WeatherController;
import controller.farm.building.CropFieldController;
import controller.farm.building.HouseController;
import gui.GUI;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.BuildingSet;
import model.farm.building.crop_field.CropField;
import viewer.GameViewer;
import viewer.farm.FarmViewer;

public class FarmController implements GameControllerState {
    private Farm farm;
    private GameController controller;
    private RealTimeToInGameTimeConverter realTimeToInGameTimeConverter;
    private WeatherController weatherController;

    public FarmController(Farm farm, GameController controller, double realSecToGameMinutesRate) {
        this.farm = farm;
        this.controller = controller;
        this.realTimeToInGameTimeConverter = new RealTimeToInGameTimeConverter(realSecToGameMinutesRate);
        this.weatherController = new SunnyController(1); // TODO maybe pass this value by argument ?
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

    public void setWeatherController(WeatherController weatherController) {
        this.weatherController = weatherController;
    }

    @Override
    public void reactMouseMovement(Position position) {}

    @Override
    public void reactMouseClick(Position position) {}

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {
        InGameTime elapsedTime = this.realTimeToInGameTimeConverter.convert(elapsedTimeSinceLastFrame);
        InGameTimeOper sum = new InGameTimeSum();

        this.farm.getTime().set(sum.apply(this.farm.getTime(), elapsedTime));

        CropFieldController cropFieldController = new CropFieldController(this.controller);
        for (CropField cropField : this.farm.getBuildings().getCropFields()) {
            cropFieldController.reactTimePassed(cropField, elapsedTime);
        }

        this.weatherController.updateWeather(this, this.farm.getWeather(), this.farm.getTime().getDay(), Math.random());
    }

    @Override
    public GameViewer getViewer() {
        return new FarmViewer(this.farm);
    }
}
