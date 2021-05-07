package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class InGameTimeTest {
    private InGameTime time;

    @BeforeEach
    void setUp() {
        time = new InGameTime(3,2, 1);
    }

    @Test
    public void testGetDay() {
        Assertions.assertEquals(3, time.getDay());
    }

    @Test
    public void testGetHourOfDay() {
        Assertions.assertEquals(2, time.getHourOfDay());
    }

    @Test
    public void testGetMinuteOfHour() {
        Assertions.assertEquals(1, time.getMinuteOfHour());
    }

    @Test
    public void testStringFormat() {
        Assertions.assertEquals("DAY 003  02:01", time.toString());
    }

    @Test
    public void testCountDownFormat() {
        Assertions.assertEquals(time.toCountdownString(), "3:02:01");
        InGameTime time2 = new InGameTime(0, 5, 40);
        Assertions.assertEquals(time2.toCountdownString(), "05:40");
    }
}
