package controller.weather;

import controller.farm.FarmController;
import model.Weather;

public class WindstormController extends WeatherController{
    public WindstormController(int currentDay) {
        super(currentDay);
    }

    @Override
    public void updateTime(FarmController farmController, Weather weather, int currentDay) {
        if (currentDay <= this.lastDay)
            return;

        this.lastDay = currentDay;

        double chance = Math.random();
        if (chance < 0.49) {
            weather.setType(Weather.TYPE.CLOUDY);
            farmController.setWeatherController(new CloudyController(currentDay));
        } else if (chance < 0.98) {
            weather.setType(Weather.TYPE.RAINY);
            farmController.setWeatherController(new RainyController(currentDay));
        }
        // else maintains WINDSTORM

    }

}
