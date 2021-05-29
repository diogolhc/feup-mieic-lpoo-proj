package model.farm.data;

import model.InGameTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeatherTest {
    @Test
    public void equalsOnlyDependsOnName() {
        Weather weather1 = new Weather("W1", 10.0);
        Weather weather2 = new Weather("W1", 1.0);
        Weather weather3 = new Weather("W2", 10.0);

        Assertions.assertEquals(weather1, weather2);
        Assertions.assertNotEquals(weather1, weather3);
        Assertions.assertNotEquals(weather2, weather3);
    }

    @Test
    public void getEffect() {
        Weather weather = new Weather("", 10.0);
        Assertions.assertEquals(600, weather.getEffect(new InGameTime(0, 1, 0)));
        Assertions.assertEquals(0, weather.getEffect(new InGameTime(0)));
        Assertions.assertEquals(10.0, weather.getEffect(new InGameTime(1)));

        weather.setEffect(0.5);
        Assertions.assertEquals(30, weather.getEffect(new InGameTime(0, 1, 0)));
        Assertions.assertEquals(0, weather.getEffect(new InGameTime(0)));
        Assertions.assertEquals(0.5, weather.getEffect(new InGameTime(1)));
    }
}
