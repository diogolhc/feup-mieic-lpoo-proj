package controller.weather;

import controller.farm.FarmController;
import model.weather.Cloudy;
import model.weather.Rainy;
import model.weather.Sunny;
import model.weather.Weather;

public class SunnyController extends WeatherController {
    public SunnyController(int currentDay) {
        super(currentDay);
    }

    @Override
    public void updateWeather(FarmController farmController, Weather weather, int currentDay) {
        if (currentDay <= this.lastDay)
            return;

        this.lastDay = currentDay;

        double chance = Math.random();
        if (chance < 0.1) {
            weather.setWeatherCondition(new Rainy());
            farmController.setWeatherController(new RainyController(currentDay));
        } else if (chance < 0.4) {
            weather.setWeatherCondition(new Cloudy());
            farmController.setWeatherController(new CloudyController(currentDay));
        }
        // else maintains SUNNY

    }
}
