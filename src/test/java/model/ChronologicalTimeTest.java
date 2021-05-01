package model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


public class ChronologicalTimeTest {

    @Test
    public void testAdvance1() {
        ChronologicalTime time = new ChronologicalTime();

        time.advance(15);

        assertEquals(15, time.getMinute());
    }

    @Test
    public void testAdvance2() {
        ChronologicalTime time = new ChronologicalTime();

        time.advance(15*60);

        assertEquals(0, time.getMinute());
    }

    @Test
    public void testAdvance3() {
        ChronologicalTime time = new ChronologicalTime();

        time.advance(24*60);

        assertEquals(0, time.getMinute());
    }

    @Test
    public void testAdvance4() {
        ChronologicalTime time = new ChronologicalTime();

        time.advance(2*24*60 + 3);

        assertEquals(3, time.getMinute());
    }

    @Test
    public void testAdvance5() {
        ChronologicalTime time = new ChronologicalTime();

        time.advance(60);

        assertEquals(1, time.getHour());
    }

    @Test
    public void testAdvance6() {
        ChronologicalTime time = new ChronologicalTime();

        time.advance(2*24*60 + 3*60 + 1);

        assertEquals(3, time.getHour());
    }

    @Test
    public void testAdvance7() {
        ChronologicalTime time = new ChronologicalTime();

        time.advance(2*24*60 + 1);

        assertEquals(2, time.getDay());
    }


}
