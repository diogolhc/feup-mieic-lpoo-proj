package model.weather;

public class Sunny implements WeatherCondition {
    // drys the land
    private static final String name = "SUNNY";
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
