package model.weather;

public class Thunderstorm implements WeatherCondition {
    // occasionally a position is hit by a thunder light
    private static final String name = "THUNDERSTORM";
    private static final double humidityRate = 0; // TODO decide when using

    @Override
    public String getWeather() {
        return name;
    }

    @Override
    public double getHumidityRate() {
        return humidityRate;
    }

}
