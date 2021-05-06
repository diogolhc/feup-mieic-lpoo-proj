package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.Cloudy;
import model.weather.Rainy;
import model.weather.Weather;

public class WindstormController extends WeatherController{
    public WindstormController(int currentDay) {
        super(currentDay);
    }

    @Override
    public void updateWeather(FarmController farmController, Weather weather, int currentDay, double chance) {
        if (currentDay <= this.lastDay)
            return;

        this.lastDay = currentDay;

        if (chance < 0.49) {
            weather.setWeatherCondition(new Cloudy());
            farmController.setWeatherController(new CloudyController(currentDay));
        } else if (chance < 0.98) {
            weather.setWeatherCondition(new Rainy());
            farmController.setWeatherController(new RainyController(currentDay));
        }
        // else maintains WINDSTORM

    }

}
