package controller.farm.weather;

import controller.farm.FarmController;
import model.weather.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CloudyControllerTest {
    WeatherControllerState weatherControllerState;
    WeatherController weatherController;
    Weather weather;

    @BeforeEach
    void setUp() {
        this.weatherControllerState = new CloudyController();
        this.weatherController = Mockito.mock(WeatherController.class);
        this.weather = Mockito.mock(Weather.class);
    }

    @Test
    void updateWeatherSame() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.53);

        Mockito.verify(weatherController, Mockito.never()).setWeatherControllerState(Mockito.any());
        Mockito.verify(weather, Mockito.never()).setWeatherCondition(Mockito.any());
    }

    @Test
    void updateWeatherSunny() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.19);

        Mockito.verify(weatherController).setWeatherControllerState(Mockito.isA(SunnyController.class));
        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Sunny.class));
    }

    @Test
    void updateWeatherRainy() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.49);

        Mockito.verify(weatherController).setWeatherControllerState(Mockito.isA(RainyController.class));
        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Rainy.class));
    }

    @Test
    void updateWeatherThunderstorm() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.5);

        Mockito.verify(weatherController).setWeatherControllerState(Mockito.isA(ThunderstormController.class));
        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Thunderstorm.class));
    }

    @Test
    void updateWeatherWindstorm() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.51);

        Mockito.verify(weatherController).setWeatherControllerState(Mockito.isA(WindstormController.class));
        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Windstorm.class));
    }

}
