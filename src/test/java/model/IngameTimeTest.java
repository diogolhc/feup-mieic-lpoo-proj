package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class IngameTimeTest {

    @Test
    public void testAdvance1() {
        IngameTime time = new IngameTime();

        time.advance(15);

        Assertions.assertEquals(15, time.getMinute());
    }

    @Test
    public void testAdvance2() {
        IngameTime time = new IngameTime();

        time.advance(15*60);

        Assertions.assertEquals(0, time.getMinute());
    }

    @Test
    public void testAdvance3() {
        IngameTime time = new IngameTime();

        time.advance(24*60);

        Assertions.assertEquals(0, time.getMinute());
    }

    @Test
    public void testAdvance4() {
        IngameTime time = new IngameTime();

        time.advance(2*24*60 + 3);

        Assertions.assertEquals(3, time.getMinute());
    }

    @Test
    public void testAdvance5() {
        IngameTime time = new IngameTime();

        time.advance(60);

        Assertions.assertEquals(1, time.getHour());
    }

    @Test
    public void testAdvance6() {
        IngameTime time = new IngameTime();

        time.advance(2*24*60 + 3*60 + 1);

        Assertions.assertEquals(3, time.getHour());
    }

    @Test
    public void testAdvance7() {
        IngameTime time = new IngameTime();

        time.advance(2*24*60 + 1);

        Assertions.assertEquals(2, time.getDay());
    }


}
