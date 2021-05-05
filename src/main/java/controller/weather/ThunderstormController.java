package controller.weather;

import controller.farm.FarmController;
import model.Weather;

public class ThunderstormController extends WeatherController {
    public ThunderstormController(int currentDay) {
        super(currentDay);
    }

    @Override
    public void updateTime(FarmController farmController, Weather weather, int currentDay) {
        if (currentDay <= this.lastDay)
            return;

        this.lastDay = currentDay;

        double chance = Math.random();
        if (chance < 0.45) {
            weather.setType(Weather.TYPE.CLOUDY);
            farmController.setWeatherController(new CloudyController(currentDay));
        } else if (chance < 0.9) {
            weather.setType(Weather.TYPE.RAINY);
            farmController.setWeatherController(new RainyController(currentDay));
        }
        // else maintains THUNDERSTORM

    }

}
