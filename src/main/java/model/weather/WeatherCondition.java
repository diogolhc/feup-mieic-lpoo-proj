package model.weather;

public interface WeatherCondition {
    String getWeather();
    double getHumidityRate(); // TODO not being used for now but it might be used as a multiplier in crop fields
}
