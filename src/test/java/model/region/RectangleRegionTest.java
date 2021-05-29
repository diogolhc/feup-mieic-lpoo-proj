package model.region;

import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleRegionTest {

    @Test
    public void negativeOrZeroSizeThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new RectangleRegion(new Position(0, 0), 1, -2));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new RectangleRegion(new Position(0, 0), -3, 9));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new RectangleRegion(new Position(0, 0), -1, 0));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new RectangleRegion(new Position(0, 0), 0, 5));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new RectangleRegion(new Position(0, 0), 90, 0));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new RectangleRegion(new Position(0, 0), -31, -2));
    }


    @Test
    public void containsPosition() {
        Region region1 = new RectangleRegion(new Position(4, 7), 4, 3);
        for (int x = 4; x < 8; x++) {
            for (int y = 7; y < 10; y++) {
                Assertions.assertTrue(region1.contains(new Position(x, y)));
            }
        }
        Assertions.assertFalse(region1.contains(new Position(3, 7)));
        Assertions.assertFalse(region1.contains(new Position(5, 6)));
        Assertions.assertFalse(region1.contains(new Position(7, 10)));
        Assertions.assertFalse(region1.contains(new Position(10, 8)));

        Region region2 = new RectangleRegion(new Position(5, 1), 1, 2);
        for (int x = 5; x < 6; x++) {
            for (int y = 1; y < 3; y++) {
                Assertions.assertTrue(region2.contains(new Position(x, y)));
            }
        }
        Assertions.assertFalse(region2.contains(new Position(5, 0)));
        Assertions.assertFalse(region2.contains(new Position(5, 4)));
        Assertions.assertFalse(region2.contains(new Position(4, 1)));
        Assertions.assertFalse(region2.contains(new Position(6, 2)));

    }

    @Test
    public void containsRegion() {
        RectangleRegion region1 = new RectangleRegion(new Position(4, 7), 10, 10);

        Assertions.assertTrue(region1.contains(new RectangleRegion(new Position(4, 8), 10, 9)));
        Assertions.assertTrue(region1.contains(new RectangleRegion(new Position(6, 9), 1, 1)));
        Assertions.assertTrue(region1.contains(new RectangleRegion(new Position(7, 7), 3, 3)));
        Assertions.assertFalse(region1.contains(new RectangleRegion(new Position(3, 7), 4, 3)));
        Assertions.assertFalse(region1.contains(new RectangleRegion(new Position(8, 10), 15, 3)));
        Assertions.assertFalse(region1.contains(new RectangleRegion(new Position(10, 15), 4, 4)));

        RectangleRegion region2 = new RectangleRegion(new Position(8, 5), 2, 1);
        Assertions.assertTrue(region2.contains(new RectangleRegion(new Position(9, 5), 1, 1)));
        Assertions.assertFalse(region2.contains(new RectangleRegion(new Position(7, 4), 6, 6)));
    }

    @Test
    public void intersectsRegion() {
        RectangleRegion region1 = new RectangleRegion(new Position(4, 7), 10, 10);

        Assertions.assertTrue(region1.intersects(new RectangleRegion(new Position(3, 7), 4, 3)));
        Assertions.assertTrue(region1.intersects(new RectangleRegion(new Position(6, 9), 1, 1)));
        Assertions.assertTrue(region1.intersects(new RectangleRegion(new Position(7, 7), 3, 30)));
        Assertions.assertFalse(region1.intersects(new RectangleRegion(new Position(30, 40), 15, 3)));
        Assertions.assertFalse(region1.intersects(new RectangleRegion(new Position(1, 1), 3, 6)));

        RectangleRegion region2 = new RectangleRegion(new Position(8, 5), 2, 1);
        Assertions.assertTrue(region2.intersects(new RectangleRegion(new Position(7, 4), 6, 6)));
        Assertions.assertFalse(region2.intersects(new RectangleRegion(new Position(10, 4), 6, 6)));
    }
}
