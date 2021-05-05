package controller.weather;

import controller.farm.FarmController;
import model.Weather;


public abstract class WeatherController {
    protected int lastDay;

    public WeatherController(int currentDay) {
        this.lastDay = currentDay;
    }

    public abstract void updateTime(FarmController farmController, Weather weather, int currentDay);

}
