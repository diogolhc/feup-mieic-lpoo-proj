package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.Cloudy;
import model.weather.Rainy;
import model.weather.Weather;

public class WindstormController extends WeatherController{
    public WindstormController(int nextMinute) {
        super(nextMinute);
    }

    @Override
    public void updateWeather(FarmController farmController, Weather weather, int currentMin, double chanceWeather, double chanceNextMinute) {
        if (currentMin < this.nextMinute)
            return;

        this.nextMinute = currentMin + minNextMin + (int)(chanceNextMinute * (maxNextMin-minNextMin));

        if (chanceWeather < 0.49) {
            weather.setWeatherCondition(new Cloudy());
            farmController.setWeatherController(new CloudyController(this.nextMinute));
        } else if (chanceWeather < 0.98) {
            weather.setWeatherCondition(new Rainy());
            farmController.setWeatherController(new RainyController(this.nextMinute));
        }
        // else maintains WINDSTORM

    }

}
