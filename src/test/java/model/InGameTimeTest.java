package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class InGameTimeTest {
    private InGameTime time;

    @BeforeEach
    void detUp() {
        time = new InGameTime(3,2, 1);
    }

    @Test
    public void testGetDay() {
        Assertions.assertEquals(time.getDay(), 3);
    }

    @Test
    public void testGetHourOfDay() {
        Assertions.assertEquals(time.getHourOfDay(), 2);
    }

    @Test
    public void testGetMinuteOfHour() {
        Assertions.assertEquals(time.getMinuteOfHour(), 1);
    }

    @Test
    public void testStringFormat() {
        Assertions.assertEquals(time.toString(), "Day 003  02:01");
    }

    @Test
    public void testCountDownFormat() {
        Assertions.assertEquals(time.toCountdownString(), "02:01");
    }


}
