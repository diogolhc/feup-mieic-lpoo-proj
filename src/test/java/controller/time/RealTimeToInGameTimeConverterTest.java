package controller.time;

import model.InGameTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RealTimeToInGameTimeConverterTest {
    private RealTimeToInGameTimeConverter realTimeToInGameTimeConverter;

    @BeforeEach
    void setUp() {
        this.realTimeToInGameTimeConverter = new RealTimeToInGameTimeConverter(2);
    }

    @Test
    void accelerateTime() {
        this.realTimeToInGameTimeConverter.accelerateTime(3);

        Assertions.assertEquals(this.realTimeToInGameTimeConverter.getRate(), 6);
    }

    @Test
    void convert() {
        Assertions.assertEquals(this.realTimeToInGameTimeConverter.convert(1000), new InGameTime(2));
    }

    @Test
    void accelerateAndConvert() {
        this.realTimeToInGameTimeConverter.accelerateTime(10);

        Assertions.assertEquals(this.realTimeToInGameTimeConverter.convert(2000), new InGameTime(40));
    }

    @Test
    void convertSeveralTimes() {
        this.realTimeToInGameTimeConverter.convert(100);
        this.realTimeToInGameTimeConverter.convert(200);
        this.realTimeToInGameTimeConverter.convert(300);

        Assertions.assertEquals(this.realTimeToInGameTimeConverter.convert(400), new InGameTime(2));
    }

    @Test
    void convertSeveralTimesWithAccelerate() {
        this.realTimeToInGameTimeConverter.accelerateTime(10);

        this.realTimeToInGameTimeConverter.convert(100);
        this.realTimeToInGameTimeConverter.convert(200);
        this.realTimeToInGameTimeConverter.convert(300);

        Assertions.assertEquals(this.realTimeToInGameTimeConverter.convert(400), new InGameTime(20));
    }

}
