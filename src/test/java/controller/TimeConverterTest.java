package controller;

import model.InGameTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeConverterTest {
    private TimeConverter timeConverter;

    @BeforeEach
    void setUp() {
        this.timeConverter = new TimeConverter(2);
    }

    @Test
    void accelerateTime() {
        this.timeConverter.setRateMultiplier(3);

        Assertions.assertEquals(6, this.timeConverter.getRate());

        this.timeConverter.setRateMultiplier(8);
        Assertions.assertEquals(16, this.timeConverter.getRate());
    }

    @Test
    void convert() {
        Assertions.assertEquals(new InGameTime(2), this.timeConverter.convert(1000));
    }

    @Test
    void accelerateAndConvert() {
        this.timeConverter.setRateMultiplier(10);

        Assertions.assertEquals(new InGameTime(40), this.timeConverter.convert(2000));
    }

    @Test
    void convertSeveralTimes() {
        Assertions.assertEquals(new InGameTime(0), this.timeConverter.convert(100));
        Assertions.assertEquals(new InGameTime(0), this.timeConverter.convert(100));
        Assertions.assertEquals(new InGameTime(0), this.timeConverter.convert(100));
        Assertions.assertEquals(new InGameTime(0), this.timeConverter.convert(100));
        Assertions.assertEquals(new InGameTime(1), this.timeConverter.convert(300));
        Assertions.assertEquals(new InGameTime(3), this.timeConverter.convert(1300));
    }

    @Test
    void convertSeveralTimesWithAccelerate() {
        this.timeConverter.setRateMultiplier(3);

        Assertions.assertEquals(new InGameTime(0), this.timeConverter.convert(100));
        Assertions.assertEquals(new InGameTime(1), this.timeConverter.convert(100));
        Assertions.assertEquals(new InGameTime(0), this.timeConverter.convert(100));
        Assertions.assertEquals(new InGameTime(3), this.timeConverter.convert(400));
    }
}
