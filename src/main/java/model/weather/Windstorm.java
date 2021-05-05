package model.weather;

public class Windstorm implements WeatherCondition {
    // everything (?) suffers damages
    private static final String name = "WINDSTORM";
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
