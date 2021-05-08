package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.Cloudy;
import model.weather.Rainy;
import model.weather.Weather;

public class SunnyController extends WeatherController {
    public SunnyController(int nextMinute) {
        super(nextMinute);
    }

    @Override
    public void updateWeather(FarmController farmController, Weather weather, int currentMin, double chanceWeather, double chanceNextMinute) {
        if (currentMin < this.nextMinute)
            return;

        this.nextMinute = currentMin + minNextMin + (int)(chanceNextMinute * (maxNextMin-minNextMin));

        if (chanceWeather < 0.1) {
            weather.setWeatherCondition(new Rainy());
            farmController.setWeatherController(new RainyController(this.nextMinute));
        } else if (chanceWeather < 0.4) {
            weather.setWeatherCondition(new Cloudy());
            farmController.setWeatherController(new CloudyController(this.nextMinute));
        }
        // else maintains SUNNY

    }

}
