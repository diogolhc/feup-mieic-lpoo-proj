package model.farm.building;

public class HouseTest {

    private House house;
    private House house2;

    @BeforeEach
    void setUp() {
        house = new House(new Position(0, 0));
        house2 = new House(new Position(5, 7));
    }

    @Test
    void isTraversable() {
        Assertions.assertFalse(house.getUntraversableRegion().contains(new Position(house.getHeight(), 0)));
        Assertions.assertFalse(house.getUntraversableRegion().contains(new Position(0, house.getHeight())));
        Assertions.assertFalse(house.getUntraversableRegion().contains(new Position(4, house.getHeight())));
        Assertions.assertFalse(house.getUntraversableRegion().contains(new Position(6, house.getHeight())));
        Assertions.assertFalse(house.getUntraversableRegion().contains(new Position(house.getWidth(), house.getHeight())));
        Assertions.assertFalse(house.getUntraversableRegion().contains(new Position(0, 0)));

        Assertions.assertTrue(house.getUntraversableRegion().contains(new Position(3, 3)));
        Assertions.assertTrue(house.getUntraversableRegion().contains(new Position(1, 0)));
        Assertions.assertTrue(house.getUntraversableRegion().contains(new Position(6, 4)));
        Assertions.assertTrue(house.getUntraversableRegion().contains(new Position(5, 0)));
        Assertions.assertTrue(house.getUntraversableRegion().contains(new Position(0, 1)));
        Assertions.assertTrue(house.getUntraversableRegion().contains(new Position(0, 5)));
        Assertions.assertTrue(house.getUntraversableRegion().contains(new Position(4, 5)));

        Assertions.assertFalse(house2.getUntraversableRegion().contains(new Position(6, 7 + house.getHeight())));
        Assertions.assertTrue(house2.getUntraversableRegion().contains(new Position(5, 8)));
    }

    @Test
    void isInInteractiveZone() {
        Assertions.assertTrue(house.getInteractiveRegion().contains(new Position(4, 6)));
        Assertions.assertTrue(house2.getInteractiveRegion().contains(new Position(9, 13)));
        Assertions.assertFalse(house.getInteractiveRegion().contains(new Position(9, 13)));
        Assertions.assertFalse(house2.getInteractiveRegion().contains(new Position(4, 6)));
        Assertions.assertFalse(house2.getInteractiveRegion().contains(new Position(0, 0)));
        Assertions.assertFalse(house.getInteractiveRegion().contains(new Position(0, 0)));
        Assertions.assertFalse(house.getInteractiveRegion().contains(new Position(3, 6)));
        Assertions.assertFalse(house2.getInteractiveRegion().contains(new Position(9, 12)));
    }

}
