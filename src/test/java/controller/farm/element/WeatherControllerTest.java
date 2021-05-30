package controller.farm.element;

import model.InGameTime;
import model.farm.Farm;
import model.farm.data.Weather;
import net.jqwik.api.*;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class WeatherControllerTest {
    private Weather weather1;
    private Weather weather2;
    private Weather weather3;
    private WeatherController weatherController;
    private Farm farm;

    @BeforeEach
    @BeforeProperty
    public void setUp() {
        farm = new Farm(20, 20);
        // Mocking allows to return the probabilities in a LinkedHashMap.
        // In practice, order does not matter because the chance value should
        // be random, so Weather uses a normal HashMap. However, when testing,
        // predictability is important so it is nice to have the probabilities
        // in some well-defined order.
        weather1 = Mockito.mock(Weather.class);
        weather2 = new Weather("w2");
        weather3 = new Weather("w3");

        LinkedHashMap<Weather, Double> map = new LinkedHashMap<>();
        map.put(weather2, 0.2);
        map.put(weather3, 0.3);
        Mockito.when(weather1.getWeatherChangeProbabilities()).thenReturn(map);
        Mockito.when(weather1.getName()).thenReturn("w1");
        farm.setCurrentWeather(weather1);

        weatherController = new WeatherController(farm, new InGameTime(30));
    }

    @Test
    public void reactTimePassed() {
        weatherController.reactTimePassed(new InGameTime(1));
        Mockito.verifyNoInteractions(weather1);
        weatherController.reactTimePassed(new InGameTime(5));
        Mockito.verifyNoInteractions(weather1);
        weatherController.reactTimePassed(new InGameTime(24));
        Mockito.verify(weather1, Mockito.times(1)).getWeatherChangeProbabilities();
        Assertions.assertTrue(weatherController.getNextMinute().getMinute() > 0);
    }

    @Test
    public void reactTimePassedExceeded() {
        weatherController.reactTimePassed(new InGameTime(1));
        Mockito.verifyNoInteractions(weather1);
        weatherController.reactTimePassed(new InGameTime(5));
        Mockito.verifyNoInteractions(weather1);
        weatherController.reactTimePassed(new InGameTime(30));
        Mockito.verify(weather1, Mockito.times(1)).getWeatherChangeProbabilities();
        Assertions.assertTrue(weatherController.getNextMinute().getMinute() > 0);
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
        farm.setCurrentWeather(weather1);
        weatherController.updateWeatherState(chance);
        if (chance < 0.2) {
            Assertions.assertEquals(weather2, farm.getCurrentWeather());
        } else if (chance < 0.5) {
            Assertions.assertEquals(weather3, farm.getCurrentWeather());
        } else {
            Assertions.assertEquals(weather1, farm.getCurrentWeather());
        }
    }
}
