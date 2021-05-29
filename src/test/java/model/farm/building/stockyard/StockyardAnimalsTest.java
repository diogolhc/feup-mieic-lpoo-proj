package model.farm.building.stockyard;

import model.Position;
import model.region.RectangleRegion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class StockyardAnimalsTest {
    StockyardAnimals stockyardAnimals;
    RectangleRegion animalsRegion;

    @BeforeEach
    void setUp() {
        animalsRegion = Mockito.mock(RectangleRegion.class);
        stockyardAnimals = new StockyardAnimals(animalsRegion, 4);
    }

    @Test
    void addAnimal() {
        stockyardAnimals.addAnimal(new Position(0, 0));
        Assertions.assertEquals(1, stockyardAnimals.getSize());
    }

    @Test
    void addSeveralAnimals() {
        Assertions.assertTrue(stockyardAnimals.isEmpty());
        stockyardAnimals.addAnimal(new Position(1, 2));
        Assertions.assertFalse(stockyardAnimals.isEmpty());
        Assertions.assertEquals(1, stockyardAnimals.getSize());
        stockyardAnimals.addAnimal(new Position(2, 4));
        Assertions.assertEquals(2, stockyardAnimals.getSize());
        stockyardAnimals.addAnimal(new Position(3, 5));
        Assertions.assertEquals(3, stockyardAnimals.getSize());
        Assertions.assertFalse(stockyardAnimals.isFull());
        stockyardAnimals.addAnimal(new Position(5, 3));
        Assertions.assertEquals(4, stockyardAnimals.getSize());
        Assertions.assertTrue(stockyardAnimals.isFull());
        stockyardAnimals.addAnimal(new Position(6, 1));
        Assertions.assertEquals(4, stockyardAnimals.getSize());
        Assertions.assertTrue(stockyardAnimals.isFull());
    }

    @Test
    void isAnimalAt() {
        Assertions.assertFalse(stockyardAnimals.isAnimalAt(new Position(0,0)));
        stockyardAnimals.addAnimal(new Position(0, 0));
        Assertions.assertTrue(stockyardAnimals.isAnimalAt(new Position(0,0)));

        Assertions.assertFalse(stockyardAnimals.isAnimalAt(new Position(2,4)));
        stockyardAnimals.addAnimal(new Position(2, 4));
        Assertions.assertTrue(stockyardAnimals.isAnimalAt(new Position(2,4)));

        Assertions.assertFalse(stockyardAnimals.isAnimalAt(new Position(1,4)));
        stockyardAnimals.addAnimal(new Position(1, 4));
        Assertions.assertTrue(stockyardAnimals.isAnimalAt(new Position(1,4)));

        Assertions.assertTrue(stockyardAnimals.isAnimalAt(new Position(0,0)));
        Assertions.assertTrue(stockyardAnimals.isAnimalAt(new Position(2,4)));

        stockyardAnimals.removeAnimal();
        stockyardAnimals.removeAnimal();
        stockyardAnimals.removeAnimal();

        Assertions.assertFalse(stockyardAnimals.isAnimalAt(new Position(0,0)));
        Assertions.assertFalse(stockyardAnimals.isAnimalAt(new Position(2,4)));
        Assertions.assertFalse(stockyardAnimals.isAnimalAt(new Position(1,4)));
    }

    @Test
    void canMoveTo() {
        Mockito.when(animalsRegion.contains(Mockito.any(Position.class))).thenReturn(true);
        stockyardAnimals.addAnimal(new Position(0,0));
        stockyardAnimals.addAnimal(new Position(1,2));
        stockyardAnimals.addAnimal(new Position(3,4));
        Assertions.assertFalse(stockyardAnimals.canAnimalMoveTo(new Position(0,0)));
        Assertions.assertFalse(stockyardAnimals.canAnimalMoveTo(new Position(1,2)));
        Assertions.assertFalse(stockyardAnimals.canAnimalMoveTo(new Position(3,4)));
        Assertions.assertTrue(stockyardAnimals.canAnimalMoveTo(new Position(1,1)));
        Assertions.assertTrue(stockyardAnimals.canAnimalMoveTo(new Position(7,7)));
        Mockito.when(animalsRegion.contains(Mockito.any(Position.class))).thenReturn(false);
        Assertions.assertFalse(stockyardAnimals.canAnimalMoveTo(new Position(0,0)));
        Assertions.assertFalse(stockyardAnimals.canAnimalMoveTo(new Position(1,2)));
        Assertions.assertFalse(stockyardAnimals.canAnimalMoveTo(new Position(3,4)));
        Assertions.assertFalse(stockyardAnimals.canAnimalMoveTo(new Position(1,1)));
        Assertions.assertFalse(stockyardAnimals.canAnimalMoveTo(new Position(7,7)));
    }

    @Test
    void removeAnimal() {
        stockyardAnimals.addAnimal(new Position(1, 2));
        stockyardAnimals.addAnimal(new Position(2, 4));
        stockyardAnimals.addAnimal(new Position(3, 5));
        stockyardAnimals.addAnimal(new Position(5, 3));

        Assertions.assertTrue(stockyardAnimals.isFull());
        stockyardAnimals.removeAnimal();
        Assertions.assertEquals(3, stockyardAnimals.getSize());
        Assertions.assertFalse(stockyardAnimals.isFull());
        stockyardAnimals.removeAnimal();
        Assertions.assertEquals(2, stockyardAnimals.getSize());
        stockyardAnimals.removeAnimal();
        Assertions.assertEquals(1, stockyardAnimals.getSize());
        Assertions.assertFalse(stockyardAnimals.isEmpty());
        stockyardAnimals.removeAnimal();
        Assertions.assertTrue(stockyardAnimals.isEmpty());
        Assertions.assertEquals(0, stockyardAnimals.getSize());
        stockyardAnimals.removeAnimal();
        Assertions.assertTrue(stockyardAnimals.isEmpty());
        Assertions.assertEquals(0, stockyardAnimals.getSize());
    }
}
