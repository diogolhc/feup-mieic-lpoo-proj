package model.weather;

public class Rainy implements WeatherCondition {
    // moist the land
    private static final String name = "RAINY";
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
