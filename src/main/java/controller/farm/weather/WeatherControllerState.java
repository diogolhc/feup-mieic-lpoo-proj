package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.Weather;

public interface WeatherControllerState {
    void updateWeather(WeatherController weatherController, Weather weather, double chanceWeather);
}
