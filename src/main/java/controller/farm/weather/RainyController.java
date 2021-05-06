package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.*;

public class RainyController extends WeatherController {
    public RainyController(int currentDay) {
        super(currentDay);
    }

    @Override
    public void updateWeather(FarmController farmController, Weather weather, int currentDay) {
        if (currentDay <= this.lastDay)
            return;

        this.lastDay = currentDay;

        double chance = Math.random();
        if (chance < 0.1) {
            weather.setWeatherCondition(new Sunny());
            farmController.setWeatherController(new SunnyController(currentDay));
        } else if (chance < 0.4) {
            weather.setWeatherCondition(new Cloudy());
            farmController.setWeatherController(new CloudyController(currentDay));
        } else if (chance < 0.5) {
            weather.setWeatherCondition(new Thunderstorm());
            farmController.setWeatherController(new ThunderstormController(currentDay));
        } else if (chance < 0.52) {
            weather.setWeatherCondition(new Windstorm());
            farmController.setWeatherController(new WindstormController(currentDay));
        }
        // else maintains RAINY

    }

}
