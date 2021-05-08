package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.*;

public class RainyController implements WeatherControllerState {
    @Override
    public void updateWeather(WeatherController weatherController, Weather weather, double chanceWeather) {
        if (chanceWeather < 0.1) {
            weather.setWeatherCondition(new Sunny());
            weatherController.setWeatherControllerState(new SunnyController());
        } else if (chanceWeather < 0.4) {
            weather.setWeatherCondition(new Cloudy());
            weatherController.setWeatherControllerState(new CloudyController());
        } else if (chanceWeather < 0.5) {
            weather.setWeatherCondition(new Thunderstorm());
            weatherController.setWeatherControllerState(new ThunderstormController());
        } else if (chanceWeather < 0.52) {
            weather.setWeatherCondition(new Windstorm());
            weatherController.setWeatherControllerState(new WindstormController());
        }
        // else maintains RAINY
    }
}
