package controller.farm.weather.state;

import controller.farm.weather.*;
import model.weather.*;

public class CloudyController implements WeatherControllerState {
    @Override
    public void updateWeather(WeatherController weatherController, Weather weather, double chanceWeather) {
        if (chanceWeather < 0.2) {
            weather.setWeatherCondition(new Sunny());
            weatherController.setWeatherControllerState(new SunnyController());
        } else if (chanceWeather < 0.5) {
            weather.setWeatherCondition(new Rainy());
            weatherController.setWeatherControllerState(new RainyController());
        } else if (chanceWeather < 0.51) {
            weather.setWeatherCondition(new Thunderstorm());
            weatherController.setWeatherControllerState(new ThunderstormController());
        } else if (chanceWeather < 0.52) {
            weather.setWeatherCondition(new Windstorm());
            weatherController.setWeatherControllerState(new WindstormController());
        }
        // else maintains CLOUDY
    }
}
