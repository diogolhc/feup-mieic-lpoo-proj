package model.weather;

public class Cloudy implements WeatherCondition {
    // no drying but no moister added either
    private static final String name = "CLOUDY";
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
