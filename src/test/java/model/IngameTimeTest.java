package model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


public class IngameTimeTest {

    @Test
    public void testAdvance1() {
        IngameTime time = new IngameTime();

        time.advance(15);

        assertEquals(15, time.getMinute());
    }

    @Test
    public void testAdvance2() {
        IngameTime time = new IngameTime();

        time.advance(15*60);

        assertEquals(0, time.getMinute());
    }

    @Test
    public void testAdvance3() {
        IngameTime time = new IngameTime();

        time.advance(24*60);

        assertEquals(0, time.getMinute());
    }

    @Test
    public void testAdvance4() {
        IngameTime time = new IngameTime();

        time.advance(2*24*60 + 3);

        assertEquals(3, time.getMinute());
    }

    @Test
    public void testAdvance5() {
        IngameTime time = new IngameTime();

        time.advance(60);

        assertEquals(1, time.getHour());
    }

    @Test
    public void testAdvance6() {
        IngameTime time = new IngameTime();

        time.advance(2*24*60 + 3*60 + 1);

        assertEquals(3, time.getHour());
    }

    @Test
    public void testAdvance7() {
        IngameTime time = new IngameTime();

        time.advance(2*24*60 + 1);

        assertEquals(2, time.getDay());
    }


}
