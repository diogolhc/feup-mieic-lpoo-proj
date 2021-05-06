package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.Weather;


public abstract class WeatherController {
    protected int lastDay;

    public WeatherController(int currentDay) {
        this.lastDay = currentDay;
    }

    public int getLastDay() {
        return lastDay;
    }

    public abstract void updateWeather(FarmController farmController, Weather weather, int currentDay, double chance);
}
