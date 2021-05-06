package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class RainyControllerTest {
    WeatherController weatherController;
    FarmController farmController;
    Weather weather;

    @BeforeEach
    void setUp() {
        this.weatherController = new RainyController(0);
        this.farmController = Mockito.mock(FarmController.class);
        this.weather = Mockito.mock(Weather.class);
    }

    @Test
    void updateWeatherSameDay() {
        this.weatherController.updateWeather(farmController, weather, 0, 0);

        Mockito.verify(farmController, Mockito.never()).setWeatherController(Mockito.any());
        Mockito.verify(weather, Mockito.never()).setWeatherCondition(Mockito.any());
    }

    @Test
    void updateWeatherSame() {
        this.weatherController.updateWeather(farmController, weather, 1, 0.6);

        Mockito.verify(farmController, Mockito.never()).setWeatherController(Mockito.any());
        Mockito.verify(weather, Mockito.never()).setWeatherCondition(Mockito.any());
    }

    @Test
    void updateWeatherSunny() {
        this.weatherController.updateWeather(farmController, weather, 1, 0.09);

        ArgumentCaptor<SunnyController> argumentCaptor = ArgumentCaptor.forClass(SunnyController.class);
        Mockito.verify(farmController).setWeatherController(argumentCaptor.capture());
        Assertions.assertEquals(1, argumentCaptor.getValue().getLastDay());

        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Sunny.class));
    }

    @Test
    void updateWeatherCloudy() {
        this.weatherController.updateWeather(farmController, weather, 1, 0.39);

        ArgumentCaptor<CloudyController> argumentCaptor = ArgumentCaptor.forClass(CloudyController.class);
        Mockito.verify(farmController).setWeatherController(argumentCaptor.capture());
        Assertions.assertEquals(1, argumentCaptor.getValue().getLastDay());

        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Cloudy.class));
    }

    @Test
    void updateWeatherThunderstorm() {
        this.weatherController.updateWeather(farmController, weather, 1, 0.49);

        ArgumentCaptor<ThunderstormController> argumentCaptor = ArgumentCaptor.forClass(ThunderstormController.class);
        Mockito.verify(farmController).setWeatherController(argumentCaptor.capture());
        Assertions.assertEquals(1, argumentCaptor.getValue().getLastDay());

        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Thunderstorm.class));
    }

    @Test
    void updateWeatherWindstorm() {
        this.weatherController.updateWeather(farmController, weather, 1, 0.51);

        ArgumentCaptor<WindstormController> argumentCaptor = ArgumentCaptor.forClass(WindstormController.class);
        Mockito.verify(farmController).setWeatherController(argumentCaptor.capture());
        Assertions.assertEquals(1, argumentCaptor.getValue().getLastDay());

        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Windstorm.class));
    }

}
