package controller;

import model.InGameTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RealTimeToInGameTimeConverterTest {
    private RealTimeToInGameTimeConverter realTimeToInGameTimeConverter;

    @BeforeEach
    void setUp() {
        this.realTimeToInGameTimeConverter = new RealTimeToInGameTimeConverter(2);
    }

    @Test
    void accelerateTime() {
        this.realTimeToInGameTimeConverter.setMultiplier(3);

        Assertions.assertEquals(6, this.realTimeToInGameTimeConverter.getRate());
    }

    @Test
    void convert() {
        Assertions.assertEquals(new InGameTime(2), this.realTimeToInGameTimeConverter.convert(1000));
    }

    @Test
    void accelerateAndConvert() {
        this.realTimeToInGameTimeConverter.setMultiplier(10);

        Assertions.assertEquals(new InGameTime(40), this.realTimeToInGameTimeConverter.convert(2000));
    }

    @Test
    void convertSeveralTimes() {
        this.realTimeToInGameTimeConverter.convert(100);
        this.realTimeToInGameTimeConverter.convert(200);
        this.realTimeToInGameTimeConverter.convert(300);

        Assertions.assertEquals(new InGameTime(2), this.realTimeToInGameTimeConverter.convert(400));
    }

    @Test
    void convertSeveralTimesWithAccelerate() {
        this.realTimeToInGameTimeConverter.setMultiplier(10);

        this.realTimeToInGameTimeConverter.convert(100);
        this.realTimeToInGameTimeConverter.convert(200);
        this.realTimeToInGameTimeConverter.convert(300);

        Assertions.assertEquals(new InGameTime(20), this.realTimeToInGameTimeConverter.convert(400));
    }

}
