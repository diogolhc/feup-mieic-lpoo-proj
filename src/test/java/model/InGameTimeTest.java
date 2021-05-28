package model;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
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
        Assertions.assertEquals("DAY 003  02:01", time.getDayTimeString());
    }

    @Test
    public void testCountDownFormat() {
        Assertions.assertEquals(time.getTimerString(), "3:02:01");
        InGameTime time2 = new InGameTime(0, 5, 40);
        Assertions.assertEquals(time2.getTimerString(), "05:40");
    }

    @Property
    public void getHourOfDayRange(@ForAll @Positive int n) {
        InGameTime inGameTime = new InGameTime(n);
        Assertions.assertTrue(0 <= inGameTime.getHourOfDay() && inGameTime.getHourOfDay() < 24);
    }

    @Property
    public void getMinuteOfHourRange(@ForAll @Positive int n) {
        InGameTime inGameTime = new InGameTime(n);
        Assertions.assertTrue(0 <= inGameTime.getMinuteOfHour() && inGameTime.getMinuteOfHour() < 60);
    }

    @Property
    public void addZero(@ForAll int n) {
        InGameTime inGameTime = new InGameTime(n);
        Assertions.assertEquals(inGameTime, inGameTime.add(new InGameTime(0)));
    }

    @Property
    public void subZero(@ForAll @Positive int n) {
        InGameTime inGameTime = new InGameTime(n);
        Assertions.assertEquals(inGameTime, inGameTime.subtract(new InGameTime(0)));
    }

    @Property
    public void random(@ForAll @IntRange(max = 100000000) int x, @ForAll @IntRange(min=100000000) int y) {
        InGameTime min = new InGameTime(x);
        InGameTime max = new InGameTime(y);

        InGameTime rand = InGameTime.getRandom(min, max);
        Assertions.assertTrue(min.getMinute() <= rand.getMinute() && rand.getMinute() <= max.getMinute());
    }

}
