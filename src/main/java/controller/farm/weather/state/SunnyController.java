package controller.farm.weather.state;

import controller.farm.weather.WeatherController;
import model.weather.Cloudy;
import model.weather.Rainy;
import model.weather.Weather;


public class SunnyController implements WeatherControllerState {
    @Override
    public void updateWeather(WeatherController weatherController, Weather weather, double chanceWeather) {
        if (chanceWeather < 0.1) {
            weather.setWeatherCondition(new Rainy());
            weatherController.setWeatherControllerState(new RainyController());
        } else if (chanceWeather < 0.4) {
            weather.setWeatherCondition(new Cloudy());
            weatherController.setWeatherControllerState(new CloudyController());
        }
        // else maintains SUNNY
    }
}
