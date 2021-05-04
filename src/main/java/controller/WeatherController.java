package controller;

import model.Weather;


public class WeatherController {
    private int lastDay;

    public WeatherController() {
        this.lastDay = 0;
    }

    public void updateTime(Weather weather, int currentDay) {
        if (currentDay <= lastDay)
            return;

        this.lastDay = currentDay;
        Weather.TYPE current = weather.getType();

        double chance = Math.random();
        switch (current) {
            case SUNNY:
                if (chance < 0.1) {
                    weather.setType(Weather.TYPE.RAINY);
                } else if (chance < 0.4) {
                    weather.setType(Weather.TYPE.CLOUDY);
                }
                // else maintains SUNNY
                break;

            case CLOUDY:
                if (chance < 0.2) {
                    weather.setType(Weather.TYPE.SUNNY);
                } else if (chance < 0.5) {
                    weather.setType(Weather.TYPE.RAINY);
                } else if (chance < 0.51) {
                    weather.setType(Weather.TYPE.THUNDERSTORM);
                } else if (chance < 0.52) {
                    weather.setType(Weather.TYPE.WINDSTORM);
                }
                // else maintains CLOUDY
                break;

            case RAINY:
                if (chance < 0.1) {
                    weather.setType(Weather.TYPE.SUNNY);
                } else if (chance < 0.4) {
                    weather.setType(Weather.TYPE.CLOUDY);
                } else if (chance < 0.5) {
                    weather.setType(Weather.TYPE.THUNDERSTORM);
                } else if (chance < 0.52) {
                    weather.setType(Weather.TYPE.WINDSTORM);
                }
                // else maintains RAINY
                break;

            case THUNDERSTORM:
                if (chance < 0.45) {
                    weather.setType(Weather.TYPE.CLOUDY);
                } else if (chance < 0.9) {
                    weather.setType(Weather.TYPE.RAINY);
                }
                // else maintains THUNDERSTORM
                break;

            case WINDSTORM:
                if (chance < 0.49) {
                    weather.setType(Weather.TYPE.CLOUDY);
                } else if (chance < 0.98) {
                    weather.setType(Weather.TYPE.RAINY);
                }
                // else maintains WINDSTORM
                break;

        }

    }
}
