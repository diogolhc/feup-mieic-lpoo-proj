package controller;

import model.Weather;

public interface WeatherObserver {
    void notifyWeatherChange(Weather.TYPE type);

}
