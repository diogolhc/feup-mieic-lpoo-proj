package model.farm.data;


import java.io.Serializable;
import model.InGameTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Weather implements Serializable {
    private final String name;
    private final double weatherEffect;
    private final Map<Weather, Double> weatherChangeProbabilities;

    public Weather(String name, double weatherEffect) {
        this.name = name;
        this.weatherEffect = weatherEffect;
        this.weatherChangeProbabilities = new HashMap<>();
    }

    public Weather(String name) {
        this(name, 0);
    }

    public static Weather parseWeatherType(List<String> lines) {
        String[] tokens = lines.get(0).split(" ");

        String name = tokens[0];
        double weatherEffect = Double.parseDouble(tokens[1]);
        Weather weather = new Weather(name, weatherEffect);

        tokens = lines.get(1).split(" ");
        int numChanges = Integer.parseInt(tokens[0]);

        for (int j = 0; j < numChanges; j++) {
            String name2 = tokens[2*j + 1];
            double probability = Double.parseDouble(tokens[2*j + 2]);

            Weather weather2 = new Weather(name2);

            // TODO
            if ((index = weathers.indexOf(weather2)) != -1) {
                weather2 = weathers.get(index);
            } else {
                weathers.add(weather2);
            }

            weather.addWeatherChangeProbability(weather2, probability);
        }

        return weather;
    }

    public String getName() {
        return name;
    }

    public double getEffect(InGameTime elapsedTime) {
        return this.weatherEffect * elapsedTime.getMinute();
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
