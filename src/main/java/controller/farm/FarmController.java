package controller.farm;

import controller.GameController;
import controller.GameControllerState;
import controller.TimeController;
import controller.weather.SunnyController;
import controller.weather.WeatherController;
import controller.farm.building.CropFieldController;
import controller.farm.building.HouseController;
import controller.weather.WindstormController;
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
    private TimeController timeController;
    private WeatherController weatherController;

    public FarmController(Farm farm, GameController controller) {
        this.farm = farm;
        this.controller = controller;
        this.timeController = new TimeController(1);
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
    public void reactTimePassed() {
        this.timeController.advanceTime(this.farm.getTime());
        this.weatherController.updateWeather(this, this.farm.getWeather(), this.farm.getTime().getDay());
    }

    @Override
    public GameViewer getViewer() {
        return new FarmViewer(this.farm);
    }
}
