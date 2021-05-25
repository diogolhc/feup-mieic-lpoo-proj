package model.farm;

import java.util.HashMap;
import java.util.Map;

public class Weather {
    private String name;
    private double weatherEffect;
    Map<Weather, Double> weatherChangeProbabilities;

    public Weather(String name, double weatherEffect) {
        this.name = name;
        this.weatherEffect = weatherEffect;
        this.weatherChangeProbabilities = new HashMap<>();
    }

    public Weather(String name) {
        this(name, 0);
    }

    public String getName() {
        return name;
    }

    public double getWeatherEffect() {
        return weatherEffect;
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

    public void setWeatherEffect(double weatherEffect) {
        this.weatherEffect = weatherEffect;
    }
}
