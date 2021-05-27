package model.farm.data;


import java.io.Serializable;
import model.InGameTime;

import java.util.HashMap;
import java.util.Map;

public class Weather implements Serializable {
    private final String name;
    private double effect;
    private final Map<Weather, Double> weatherChangeProbabilities;

    public Weather(String name, double effect) {
        this.name = name;
        this.effect = effect;
        this.weatherChangeProbabilities = new HashMap<>();
    }

    public Weather(String name) {
        this(name, 0);
    }

    public String getName() {
        return name;
    }

    public double getEffect(InGameTime elapsedTime) {
        return this.effect * elapsedTime.getMinute();
    }

    public void setEffect(double weatherEffect) {
        this.effect = weatherEffect;
    }

    public Map<Weather, Double> getWeatherChangeProbabilities() {
        return this.weatherChangeProbabilities;
    }

    public void addWeatherChangeProbability(Weather weather, double probability) {
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
