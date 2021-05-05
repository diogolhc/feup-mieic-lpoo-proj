package controller.weather;

import controller.farm.FarmController;
import model.weather.*;

public class CloudyController extends WeatherController{
    public CloudyController(int currentDay) {
        super(currentDay);
    }

    @Override
    public void updateWeather(FarmController farmController, Weather weather, int currentDay) {
        if (currentDay <= this.lastDay)
            return;

        this.lastDay = currentDay;

        double chance = Math.random();
        if (chance < 0.2) {
            weather.setWeatherCondition(new Sunny());
            farmController.setWeatherController(new SunnyController(currentDay));
        } else if (chance < 0.5) {
            weather.setWeatherCondition(new Rainy());
            farmController.setWeatherController(new RainyController(currentDay));
        } else if (chance < 0.51) {
            weather.setWeatherCondition(new Thunderstorm());
            farmController.setWeatherController(new ThunderstormController(currentDay));
        } else if (chance < 0.52) {
            weather.setWeatherCondition(new Windstorm());
            farmController.setWeatherController(new WindstormController(currentDay));
        }
        // else maintains CLOUDY

    }

}
