package controller.farm.weather.state;

import controller.farm.FarmController;
import controller.farm.weather.WeatherController;
import model.weather.Weather;

public interface WeatherControllerState {
    void updateWeather(WeatherController weatherController, Weather weather, double chanceWeather);
}
