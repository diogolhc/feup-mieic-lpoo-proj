package controller.weather;

import controller.farm.FarmController;
import model.Weather;

public class CloudyController extends WeatherController{
    public CloudyController(int currentDay) {
        super(currentDay);
    }

    @Override
    public void updateTime(FarmController farmController, Weather weather, int currentDay) {
        if (currentDay <= this.lastDay)
            return;

        this.lastDay = currentDay;

        double chance = Math.random();
        if (chance < 0.2) {
            weather.setType(Weather.TYPE.SUNNY);
            farmController.setWeatherController(new SunnyController(currentDay));
        } else if (chance < 0.5) {
            weather.setType(Weather.TYPE.RAINY);
            farmController.setWeatherController(new RainyController(currentDay));
        } else if (chance < 0.51) {
            weather.setType(Weather.TYPE.THUNDERSTORM);
            farmController.setWeatherController(new ThunderstormController(currentDay));
        } else if (chance < 0.52) {
            weather.setType(Weather.TYPE.WINDSTORM);
            farmController.setWeatherController(new WindstormController(currentDay));
        }
        // else maintains CLOUDY

    }

}
