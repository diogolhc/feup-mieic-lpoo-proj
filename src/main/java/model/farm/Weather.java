package model.farm;

import java.util.HashMap;
import java.util.Map;

public class Weather {
    private String name;
    private double humidityRate; // TODO decide when using
    Map<Weather, Double> weatherChangeProbabilities;

    public Weather(String name, double humidityRate) {
        this.name = name;
        this.humidityRate = humidityRate;
        this.weatherChangeProbabilities = new HashMap<>();
    }

    public Weather(String name) {
        this(name, 0);
    }

    public String getName() {
        return name;
    }

    public double getHumidityRate() {
        return humidityRate;
    }

    public Map<Weather, Double> getWeatherChangeProbabilities() {
        return weatherChangeProbabilities;
    }

    public void addWeatherChangePossibility(Weather weather, double probability) {
        this.weatherChangeProbabilities.put(weather, probability);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return this.name.equals(weather.getName());
    }

}
