package model.farm;

import model.Position;
import model.farm.building.Edifice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FarmTest {
    private Farm farm;

    @BeforeEach
    void setUp() {
        farm = new Farm(40, 20);
        farm.getBuildings().setHousePosition(new Position(1, 1));
    }

    @Test
    void isTraversable() {
        Assertions.assertTrue(farm.isTraversable(new Position(38, 18)));
        Assertions.assertTrue(farm.isTraversable(new Position(10, 10)));
        Assertions.assertTrue(farm.isTraversable(new Position(3, 7)));
        Assertions.assertTrue(farm.isTraversable(new Position(1, 1)));
        Assertions.assertTrue(farm.isTraversable(new Position(farm.getHeight() - 2, 1)));
        Assertions.assertTrue(farm.isTraversable(new Position(10, 3)));
    }

    @Test
    void outsideFarmLimits() {
        Assertions.assertFalse(farm.isTraversable(new Position(39, 29)));
        Assertions.assertFalse(farm.isTraversable(new Position(0, 0)));
        Assertions.assertFalse(farm.isTraversable(new Position(0, 3)));
        Assertions.assertFalse(farm.isTraversable(new Position(4, 0)));
        Assertions.assertFalse(farm.isTraversable(new Position(farm.getWidth(), 0)));
        Assertions.assertFalse(farm.isTraversable(new Position(farm.getWidth() - 1, 3)));
        Assertions.assertFalse(farm.isTraversable(new Position(1, farm.getHeight())));
        Assertions.assertFalse(farm.isTraversable(new Position(3, farm.getHeight() - 1)));
        Assertions.assertFalse(farm.isTraversable(new Position(-5, 5)));
        Assertions.assertFalse(farm.isTraversable(new Position(farm.getWidth() + 3, farm.getHeight() + 10)));
    }

    @Test
    void obstructedByBuilding() {
        Assertions.assertFalse(farm.isTraversable(new Position(4, 4)));
        Assertions.assertFalse(farm.isTraversable(new Position(3, 6)));
    }
}
