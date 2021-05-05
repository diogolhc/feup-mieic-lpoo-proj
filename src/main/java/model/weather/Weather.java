package model.weather;


public class Weather {
    private WeatherCondition weatherCondition;

    public Weather(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public WeatherCondition getWeatherCondition() {
        return this.weatherCondition;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }
}
