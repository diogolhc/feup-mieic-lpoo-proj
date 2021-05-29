package model.region;

import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EdificeUntraversableRegionTest {
    @Test
    public void contains() {
        Region region = new EdificeUntraversableRegion(new Position(5, 5));

        for (int x = 5; x < 12; x++) {
            for (int y = 5; y < 11; y++) {
                Position position = new Position(x, y);
                if (position.equals(new Position(5, 5)) || position.equals(new Position(11, 5)) ) {
                    Assertions.assertFalse(region.contains(position));
                } else {
                    Assertions.assertTrue(region.contains(position));
                }
            }
        }
    }

    @Test
    public void containsOutside7x6Rectangle() {
        Region region = new EdificeUntraversableRegion(new Position(4, 4));

        for (int x = 3; x < 12; x++) {
            Assertions.assertFalse(region.contains(new Position(x, 3)));
            Assertions.assertFalse(region.contains(new Position(x, 10)));
        }

        for (int y = 3; y < 11; y++) {
            Assertions.assertFalse(region.contains(new Position(3, y)));
            Assertions.assertFalse(region.contains(new Position(11, y)));
        }

        Assertions.assertFalse(region.contains(new Position(0, 0)));
        Assertions.assertFalse(region.contains(new Position(37, 43)));
    }
}
