package controller.farm.weather;

import model.weather.Cloudy;
import model.weather.Rainy;
import model.weather.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class WindstormControllerTest {
    WeatherControllerState weatherControllerState;
    WeatherController weatherController;
    Weather weather;

    @BeforeEach
    void setUp() {
        this.weatherControllerState = new WindstormController();
        this.weatherController = Mockito.mock(WeatherController.class);
        this.weather = Mockito.mock(Weather.class);
    }

    @Test
    void updateWeatherSame() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.99);

        Mockito.verify(weatherController, Mockito.never()).setWeatherControllerState(Mockito.any());
        Mockito.verify(weather, Mockito.never()).setWeatherCondition(Mockito.any());
    }

    @Test
    void updateWeatherCloudy() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.1);

        Mockito.verify(weatherController).setWeatherControllerState(Mockito.isA(CloudyController.class));
        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Cloudy.class));
    }

    @Test
    void updateWeatherRainy() {
        this.weatherControllerState.updateWeather(weatherController, weather, 0.5);

        Mockito.verify(weatherController).setWeatherControllerState(Mockito.isA(RainyController.class));
        Mockito.verify(weather).setWeatherCondition(Mockito.isA(Rainy.class));
    }

}
