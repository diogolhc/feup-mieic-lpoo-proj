package controller.farm.weather;

import model.weather.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RainyControllerTest {
    WeatherControllerState weatherControllerState;
    WeatherController weatherController;
    Weather weather;

    @BeforeEach
    void setUp() {
        this.weatherControllerState = new RainyController();
        this.weatherController = Mockito.mock(WeatherController.class);
        this.weather = Mockito.mock(Weather.class);
    }

    @Test
    void updateWeatherSame() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.6);

        Mockito.verify(weatherController, Mockito.never()).setWeatherControllerState(Mockito.any());
        Mockito.verify(weather, Mockito.never()).setWeatherCondition(Mockito.any());
    }

    @Test
    void updateWeatherSunny() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.09);

        Mockito.verify(weatherController).setWeatherControllerState(Mockito.isA(SunnyController.class));
        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Sunny.class));
    }

    @Test
    void updateWeatherCloudy() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.39);

        Mockito.verify(weatherController).setWeatherControllerState(Mockito.isA(CloudyController.class));
        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Cloudy.class));
    }

    @Test
    void updateWeatherThunderstorm() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.49);

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
