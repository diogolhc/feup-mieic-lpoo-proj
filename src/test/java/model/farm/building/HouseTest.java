package model.farm.building;

import model.Position;
import model.farm.building.edifice.House;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HouseTest {
    /*
    private House house;
    private House house2;

    @BeforeEach
    void setUp() {
        house = new House(new Position(0, 0));
        house2 = new House(new Position(5, 7));
    }

    @Test
    void isTraversable() {
        Assertions.assertTrue(house.getTraversableRegion(new Position(0, 0)));
        Assertions.assertTrue(house.getTraversableRegion(new Position(House.HOUSE_SIZE, 0)));
        Assertions.assertTrue(house.getTraversableRegion(new Position(0, House.HOUSE_SIZE)));
        Assertions.assertTrue(house.getTraversableRegion(new Position(4, House.HOUSE_SIZE)));
        Assertions.assertTrue(house.getTraversableRegion(new Position(6, House.HOUSE_SIZE)));

        Assertions.assertFalse(house.getTraversableRegion(new Position(3, 3)));
        Assertions.assertFalse(house.getTraversableRegion(new Position(1, 0)));
        Assertions.assertFalse(house.getTraversableRegion(new Position(6, 4)));
        Assertions.assertFalse(house.getTraversableRegion(new Position(5, 0)));
        Assertions.assertFalse(house.getTraversableRegion(new Position(0, 1)));
        Assertions.assertFalse(house.getTraversableRegion(new Position(0, 5)));
        Assertions.assertFalse(house.getTraversableRegion(new Position(4, 5)));

        Assertions.assertTrue(house2.getTraversableRegion(new Position(6, 7+House.HOUSE_SIZE)));
        Assertions.assertFalse(house2.getTraversableRegion(new Position(5, 8)));
    }

    @Test
    void isInInteractiveZone() {
        Assertions.assertTrue(house.getInteractiveRegion(new Position(4, 6)));
        Assertions.assertTrue(house2.getInteractiveRegion(new Position(9, 13)));
        Assertions.assertFalse(house.getInteractiveRegion(new Position(9, 13)));
        Assertions.assertFalse(house2.getInteractiveRegion(new Position(4, 6)));
        Assertions.assertFalse(house2.getInteractiveRegion(new Position(0, 0)));
        Assertions.assertFalse(house.getInteractiveRegion(new Position(0, 0)));
        Assertions.assertFalse(house.getInteractiveRegion(new Position(3, 6)));
        Assertions.assertFalse(house2.getInteractiveRegion(new Position(9, 12)));
    }

     */
}
