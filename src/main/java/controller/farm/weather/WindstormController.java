package controller.farm.weather;

import model.weather.Cloudy;
import model.weather.Rainy;
import model.weather.Weather;

public class WindstormController implements WeatherControllerState{
    @Override
    public void updateWeather(WeatherController weatherController, Weather weather, double chanceWeather) {
        if (chanceWeather < 0.49) {
            weather.setWeatherCondition(new Cloudy());
            weatherController.setWeatherControllerState(new CloudyController());
        } else if (chanceWeather < 0.98) {
            weather.setWeatherCondition(new Rainy());
            weatherController.setWeatherControllerState(new RainyController());
        }
        // else maintains WINDSTORM
    }
}
