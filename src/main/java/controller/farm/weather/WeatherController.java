package controller.farm.weather;

import model.InGameTime;
import model.weather.*;


public class WeatherController {
    private static final int minNextMin = 10; // TODO adjust these values (?)
    private static final int maxNextMin = 100;
    private WeatherControllerState weatherControllerState;
    private Weather weather;
    private int nextMinute;

    public WeatherController(Weather weather) {
        this.nextMinute = 0;
        this.weather = weather;

        // TODO this won't be needed after refactoring Weather
        //      and weather controller
        WeatherCondition weatherCondition = weather.getWeatherCondition();
        if (weatherCondition instanceof Cloudy) {
            this.weatherControllerState = new CloudyController();
        } else if (weatherCondition instanceof Rainy) {
            this.weatherControllerState = new RainyController();
        } else if (weatherCondition instanceof Sunny) {
            this.weatherControllerState = new SunnyController();
        } else if (weatherCondition instanceof Thunderstorm) {
            this.weatherControllerState = new ThunderstormController();
        } else if (weatherCondition instanceof Windstorm) {
            this.weatherControllerState = new WindstormController();
        } else {
            // This should never happen
            throw new RuntimeException(
                    "LOGIC ERROR: Unhandled WeatherCondition: " + weatherCondition.getClass().toString());
        }
    }

    public void setWeatherControllerState(WeatherControllerState weatherControllerState) {
        this.weatherControllerState = weatherControllerState;
    }

    public void reactTimePassed(InGameTime time) {
        this.nextMinute -= time.getMinute();
        if (this.nextMinute <= 0)
            this.updateWeatherState(Math.random());
    }

    private void updateWeatherState(double chance) {
        this.weatherControllerState.updateWeather(this, this.weather, chance);
        this.nextMinute = minNextMin + (int)(Math.random() * (maxNextMin-minNextMin));
    }

}
