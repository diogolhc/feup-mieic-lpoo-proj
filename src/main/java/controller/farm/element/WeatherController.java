package controller.farm.element;

import model.InGameTime;
import model.farm.Farm;
import model.farm.data.Weather;

import java.util.Map;

public class WeatherController {
    private static final InGameTime MIN_NEXT_MINUTE = new InGameTime(15);
    private static final InGameTime MAX_NEXT_MINUTE = new InGameTime(120);
    private InGameTime nextMinute;
    private final Farm farm;

    public WeatherController(Farm farm) {
        this.nextMinute = InGameTime.getRandom(MIN_NEXT_MINUTE, MAX_NEXT_MINUTE);
        this.farm = farm;
    }

    public void reactTimePassed(InGameTime time) {
        this.nextMinute = this.nextMinute.subtract(time);
        if (this.nextMinute.getMinute() <= 0) {
            this.updateWeatherState(Math.random());
            this.nextMinute = InGameTime.getRandom(MIN_NEXT_MINUTE, MAX_NEXT_MINUTE);
        }
    }

    private void updateWeatherState(double chance) {
        Map<Weather, Double> probabilities = this.farm.getCurrentWeather().getWeatherChangeProbabilities();

        double acum = 0;
        for (Map.Entry<Weather, Double> entry: probabilities.entrySet()) {
            acum += entry.getValue();

            if (chance < acum) {
                this.farm.setCurrentWeather(entry.getKey());
                break;
            }
        }

    }
}
