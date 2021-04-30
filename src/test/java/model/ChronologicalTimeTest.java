package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChronologicalTimeTest {

    @Test
    public void testAdvance() {
        ChronologicalTime time = new ChronologicalTime(0);

        time.advance(15);

        assertEquals(15, time.getMinutes());
    }

    @Test
    public void testGetDay() {
        ChronologicalTime time = new ChronologicalTime(0);

        time.advance(24*60*60*2 + 1);

        assertEquals(3, time.getDay());
    }

    @Test
    public void testGetHourOfDay() {
        ChronologicalTime time = new ChronologicalTime(0);

        time.advance(24*60*60*2 + 60*2 + 1);

        assertEquals(3, time.getHourOfDay());
    }

    @Test
    public void testGetMinuteOfHour() {
        ChronologicalTime time = new ChronologicalTime(0);

        time.advance(24*60*60*2 + 60*2 + 1);

        assertEquals(1, time.getMinuteOfHour());
    }
}