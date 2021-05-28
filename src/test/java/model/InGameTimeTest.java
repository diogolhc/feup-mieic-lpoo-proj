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

    @Test
    public void timeDoesNotGoBelowZero() {
        Assertions.assertEquals(new InGameTime(0), new InGameTime(-1));
        Assertions.assertEquals(new InGameTime(0), new InGameTime(-500));
    }

    @Test
    public void dayAndHourCarry() {
        Assertions.assertEquals(new InGameTime(0, 0, 1), new InGameTime(1));
        Assertions.assertEquals(new InGameTime(0, 1, 0), new InGameTime(60));
        Assertions.assertEquals(new InGameTime(1, 0, 0), new InGameTime(1440));
        Assertions.assertEquals(new InGameTime(1, 0, 0), new InGameTime(0, 24, 0));
    }

    @Property
    public void testGetDayAndTimeOfDay(@ForAll @Positive @IntRange(max = 999) int day,
                                       @ForAll @Positive @IntRange(max = 23) int hour,
                                       @ForAll @Positive @IntRange(max = 59) int minute) {
        time = new InGameTime(day, hour, minute);
        Assertions.assertEquals(day, time.getDay());
        Assertions.assertEquals(hour, time.getHourOfDay());
        Assertions.assertEquals(minute, time.getMinuteOfHour());
    }

    @Test
    public void testParseTimerString() {
        Assertions.assertEquals(new InGameTime(0, 15, 23), InGameTime.parseTimerString("0:15:23"));
        Assertions.assertEquals(new InGameTime(0, 15, 23), InGameTime.parseTimerString("000:15:23"));
        Assertions.assertEquals(new InGameTime(0, 15, 23), InGameTime.parseTimerString("15:23"));

        Assertions.assertEquals(new InGameTime(0, 9, 4), InGameTime.parseTimerString("09:04"));
        Assertions.assertEquals(new InGameTime(0, 2, 9), InGameTime.parseTimerString("2:9"));

        Assertions.assertEquals(new InGameTime(2, 0, 54), InGameTime.parseTimerString("48:54"));
    }

    @Test
    public void testParseTimerStringInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> InGameTime.parseTimerString(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> InGameTime.parseTimerString("40"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> InGameTime.parseTimerString("12:15:08:07"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> InGameTime.parseTimerString("hello"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> InGameTime.parseTimerString("h:08:07"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> InGameTime.parseTimerString("O8:O7"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> InGameTime.parseTimerString("DAY 000 08:07"));
    }

    @Test
    public void testDayTimeString() {
        time = new InGameTime(3,2, 1);
        Assertions.assertEquals("DAY 003  02:01", time.getDayTimeString());
        time = new InGameTime(0, 5, 40);
        Assertions.assertEquals("DAY 000  05:40", time.getDayTimeString());
        time = new InGameTime(0, 2, 2);
        Assertions.assertEquals("DAY 000  02:02", time.getDayTimeString());
    }

    @Test
    public void testTimerString() {
        time = new InGameTime(3,2, 1);
        Assertions.assertEquals("3:02:01", time.getTimerString());
        time = new InGameTime(0, 5, 40);
        Assertions.assertEquals("05:40", time.getTimerString());
        time = new InGameTime(0, 2, 2);
        Assertions.assertEquals("02:02", time.getTimerString());
    }

    @Property
    public void getRandomIsInRange(@ForAll @Positive int min, @ForAll @Positive int max) {
        InGameTime minTime = new InGameTime(min);
        InGameTime maxTime = new InGameTime(max);

        if (max > min) {
            InGameTime time = InGameTime.getRandom(minTime, maxTime);

            Assertions.assertNotEquals(-1, time.compareTo(minTime));
            Assertions.assertEquals(-1, time.compareTo(maxTime));
        } else if (min > max) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> InGameTime.getRandom(minTime, maxTime));
        } else {
            InGameTime time = InGameTime.getRandom(minTime, maxTime);

            Assertions.assertEquals(time, minTime);
        }
    }
}
