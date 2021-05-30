package controller.farm.element;

import model.InGameTime;
import model.farm.Farm;
import model.farm.data.Weather;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedHashMap;

public class WeatherControllerTest {
    private Weather weather1;
    private Weather weather2;
    private Weather weather3;
    private WeatherController weatherController;
    private Farm farm;

    @BeforeEach
    @BeforeProperty
    public void setUp() {
        this.farm = new Farm(20, 20);
        // Mocking allows to return the probabilities in a LinkedHashMap.
        // In practice, order does not matter because the chance value should
        // be random, so Weather uses a normal HashMap. However, when testing,
        // predictability is important so it is nice to have the probabilities
        // in some well-defined order.
        this.weather1 = Mockito.mock(Weather.class);
        this.weather2 = new Weather("w2");
        this.weather3 = new Weather("w3");

        LinkedHashMap<Weather, Double> map = new LinkedHashMap<>();
        map.put(this.weather2, 0.2);
        map.put(this.weather3, 0.3);
        Mockito.when(this.weather1.getWeatherChangeProbabilities()).thenReturn(map);
        Mockito.when(this.weather1.getName()).thenReturn("w1");
        this.farm.setCurrentWeather(this.weather1);

        this.weatherController = new WeatherController(this.farm, new InGameTime(30));
    }

    @Test
    public void reactTimePassed() {
        this.weatherController.reactTimePassed(new InGameTime(1));
        Mockito.verifyNoInteractions(this.weather1);
        this.weatherController.reactTimePassed(new InGameTime(5));
        Mockito.verifyNoInteractions(this.weather1);
        this.weatherController.reactTimePassed(new InGameTime(24));
        Mockito.verify(this.weather1, Mockito.times(1)).getWeatherChangeProbabilities();
        Assertions.assertTrue(this.weatherController.getNextMinute().getMinute() > 0);
    }

    @Test
    public void reactTimePassedExceeded() {
        this.weatherController.reactTimePassed(new InGameTime(1));
        Mockito.verifyNoInteractions(this.weather1);
        this.weatherController.reactTimePassed(new InGameTime(5));
        Mockito.verifyNoInteractions(this.weather1);
        this.weatherController.reactTimePassed(new InGameTime(30));
        Mockito.verify(this.weather1, Mockito.times(1)).getWeatherChangeProbabilities();
        Assertions.assertTrue(this.weatherController.getNextMinute().getMinute() > 0);
    }

    @Provide
    public Arbitrary<Double> probability() {
        return Arbitraries
                .doubles()
                .between(0.0, 1.0)
                .edgeCases(config -> config.add(0.0, 0.2, 0.5, 1.0));
    }

    @Property
    public void updateWeatherState(@ForAll("probability") double chance) {
        this.farm.setCurrentWeather(this.weather1);
        this.weatherController.updateWeatherState(chance);
        if (chance < 0.2) {
            Assertions.assertEquals(this.weather2, this.farm.getCurrentWeather());
        } else if (chance < 0.5) {
            Assertions.assertEquals(this.weather3, this.farm.getCurrentWeather());
        } else {
            Assertions.assertEquals(this.weather1, this.farm.getCurrentWeather());
        }
    }
}
