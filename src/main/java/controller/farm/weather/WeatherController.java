package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.Weather;


public abstract class WeatherController {
    protected static final int minNextMin = 10; // TODO adjust these values (?)
    protected static final int maxNextMin = 100;
    protected int nextMinute;

    public WeatherController(int nextMinute) {
        this.nextMinute = nextMinute;
    }

    public int getNextMinute() {
        return nextMinute;
    }

    public abstract void updateWeather(FarmController farmController, Weather weather, int currentMin, double chanceWeather, double chanceNextMinute);
}
