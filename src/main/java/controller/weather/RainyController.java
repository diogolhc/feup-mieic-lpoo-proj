package controller.weather;

import controller.farm.FarmController;
import model.Weather;

public class RainyController extends WeatherController {
    public RainyController(int currentDay) {
        super(currentDay);
    }

    @Override
    public void updateTime(FarmController farmController, Weather weather, int currentDay) {
        if (currentDay <= this.lastDay)
            return;

        this.lastDay = currentDay;

        double chance = Math.random();
        if (chance < 0.1) {
            weather.setType(Weather.TYPE.SUNNY);
            farmController.setWeatherController(new SunnyController(currentDay));
        } else if (chance < 0.4) {
            weather.setType(Weather.TYPE.CLOUDY);
            farmController.setWeatherController(new CloudyController(currentDay));
        } else if (chance < 0.5) {
            weather.setType(Weather.TYPE.THUNDERSTORM);
            farmController.setWeatherController(new ThunderstormController(currentDay));
        } else if (chance < 0.52) {
            weather.setType(Weather.TYPE.WINDSTORM);
            farmController.setWeatherController(new WindstormController(currentDay));
        }
        // else maintains RAINY

    }

}
