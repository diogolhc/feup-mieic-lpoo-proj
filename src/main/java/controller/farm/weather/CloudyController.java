package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.*;

public class CloudyController extends WeatherController{
    public CloudyController(int nextMinute) {
        super(nextMinute);
    }

    @Override
    public void updateWeather(FarmController farmController, Weather weather, int currentMin, double chanceWeather, double chanceNextMinute) {
        if (currentMin < this.nextMinute)
            return;

        this.nextMinute = currentMin + minNextMin + (int)(chanceNextMinute * (maxNextMin-minNextMin));

        if (chanceWeather < 0.2) {
            weather.setWeatherCondition(new Sunny());
            farmController.setWeatherController(new SunnyController(this.nextMinute));
        } else if (chanceWeather < 0.5) {
            weather.setWeatherCondition(new Rainy());
            farmController.setWeatherController(new RainyController(this.nextMinute));
        } else if (chanceWeather < 0.51) {
            weather.setWeatherCondition(new Thunderstorm());
            farmController.setWeatherController(new ThunderstormController(this.nextMinute));
        } else if (chanceWeather < 0.52) {
            weather.setWeatherCondition(new Windstorm());
            farmController.setWeatherController(new WindstormController(this.nextMinute));
        }
        // else maintains CLOUDY
    }
}
