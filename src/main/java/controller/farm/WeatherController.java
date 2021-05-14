package controller.farm;

import model.InGameTime;
import model.farm.Farm;
import model.farm.Weather;

import java.util.Map;


public class WeatherController {
    private static final int minNextMin = 15; // TODO adjust these values (?)
    private static final int maxNextMin = 120;
    private int nextMinute;
    Farm farm;

    public WeatherController(Farm farm) {
        this.nextMinute = 0;
        this.farm = farm;
    }

    public void reactTimePassed(InGameTime time) {
        this.nextMinute -= time.getMinute();
        if (this.nextMinute <= 0) {
            this.updateWeatherState(Math.random());
            this.nextMinute = minNextMin + (int)(Math.random() * (maxNextMin-minNextMin));
        }
    }

    private void updateWeatherState(double chance) {
        Map<Weather, Double> probabilities = farm.getWeather().getWeatherChangeProbabilities();

        double acum = 0;
        for (Map.Entry<Weather, Double> entry : probabilities.entrySet()) {
            acum += entry.getValue();

            if (chance < acum) {
                this.farm.setWeather(entry.getKey());
                break;
            }
        }

    }

}
