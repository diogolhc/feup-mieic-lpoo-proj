package controller;

import model.Weather;

import java.util.HashSet;
import java.util.Set;


public class WeatherListener {
    Set<WeatherObserver> listeners;

    public WeatherListener() {
        this.listeners = new HashSet<>();
    }

    public void notifyWeatherChange(Weather.TYPE type) {
        for (WeatherObserver listener : listeners) {
            listener.notifyWeatherChange(type);
        }
    }

    public void addListener(WeatherObserver listener) {
        this.listeners.add(listener);
    }

    public void removeListener(WeatherObserver listener) {
        this.listeners.remove(listener);
    }

    public void reset() {
        this.listeners.clear();
    }

}
