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
        this.animalsRegion = Mockito.mock(RectangleRegion.class);
        this.stockyardAnimals = new StockyardAnimals(this.animalsRegion, 4);
    }

    @Test
    void addAnimal() {
        this.stockyardAnimals.addAnimal(new Position(0, 0));
        Assertions.assertEquals(1, this.stockyardAnimals.getSize());
    }

    @Test
    void addSeveralAnimals() {
        Assertions.assertTrue(this.stockyardAnimals.isEmpty());
        this.stockyardAnimals.addAnimal(new Position(1, 2));
        Assertions.assertFalse(this.stockyardAnimals.isEmpty());
        Assertions.assertEquals(1, this.stockyardAnimals.getSize());
        this.stockyardAnimals.addAnimal(new Position(2, 4));
        Assertions.assertEquals(2, this.stockyardAnimals.getSize());
        this.stockyardAnimals.addAnimal(new Position(3, 5));
        Assertions.assertEquals(3, this.stockyardAnimals.getSize());
        Assertions.assertFalse(this.stockyardAnimals.isFull());
        this.stockyardAnimals.addAnimal(new Position(5, 3));
        Assertions.assertEquals(4, this.stockyardAnimals.getSize());
        Assertions.assertTrue(this.stockyardAnimals.isFull());
        this.stockyardAnimals.addAnimal(new Position(6, 1));
        Assertions.assertEquals(4, this.stockyardAnimals.getSize());
        Assertions.assertTrue(this.stockyardAnimals.isFull());
    }

    @Test
    void isAnimalAt() {
        Assertions.assertFalse(this.stockyardAnimals.isAnimalAt(new Position(0,0)));
        this.stockyardAnimals.addAnimal(new Position(0, 0));
        Assertions.assertTrue(this.stockyardAnimals.isAnimalAt(new Position(0,0)));

        Assertions.assertFalse(this.stockyardAnimals.isAnimalAt(new Position(2,4)));
        this.stockyardAnimals.addAnimal(new Position(2, 4));
        Assertions.assertTrue(this.stockyardAnimals.isAnimalAt(new Position(2,4)));

        Assertions.assertFalse(this.stockyardAnimals.isAnimalAt(new Position(1,4)));
        this.stockyardAnimals.addAnimal(new Position(1, 4));
        Assertions.assertTrue(this.stockyardAnimals.isAnimalAt(new Position(1,4)));

        Assertions.assertTrue(this.stockyardAnimals.isAnimalAt(new Position(0,0)));
        Assertions.assertTrue(this.stockyardAnimals.isAnimalAt(new Position(2,4)));

        this.stockyardAnimals.removeAnimal();
        this.stockyardAnimals.removeAnimal();
        this.stockyardAnimals.removeAnimal();

        Assertions.assertFalse(this.stockyardAnimals.isAnimalAt(new Position(0,0)));
        Assertions.assertFalse(this.stockyardAnimals.isAnimalAt(new Position(2,4)));
        Assertions.assertFalse(this.stockyardAnimals.isAnimalAt(new Position(1,4)));
    }

    @Test
    void canMoveTo() {
        Mockito.when(this.animalsRegion.contains(Mockito.any(Position.class))).thenReturn(true);
        this.stockyardAnimals.addAnimal(new Position(0,0));
        this.stockyardAnimals.addAnimal(new Position(1,2));
        this.stockyardAnimals.addAnimal(new Position(3,4));
        Assertions.assertFalse(this.stockyardAnimals.canAnimalMoveTo(new Position(0,0)));
        Assertions.assertFalse(this.stockyardAnimals.canAnimalMoveTo(new Position(1,2)));
        Assertions.assertFalse(this.stockyardAnimals.canAnimalMoveTo(new Position(3,4)));
        Assertions.assertTrue(this.stockyardAnimals.canAnimalMoveTo(new Position(1,1)));
        Assertions.assertTrue(this.stockyardAnimals.canAnimalMoveTo(new Position(7,7)));
        Mockito.when(this.animalsRegion.contains(Mockito.any(Position.class))).thenReturn(false);
        Assertions.assertFalse(this.stockyardAnimals.canAnimalMoveTo(new Position(0,0)));
        Assertions.assertFalse(this.stockyardAnimals.canAnimalMoveTo(new Position(1,2)));
        Assertions.assertFalse(this.stockyardAnimals.canAnimalMoveTo(new Position(3,4)));
        Assertions.assertFalse(this.stockyardAnimals.canAnimalMoveTo(new Position(1,1)));
        Assertions.assertFalse(this.stockyardAnimals.canAnimalMoveTo(new Position(7,7)));
    }

    @Test
    void removeAnimal() {
        this.stockyardAnimals.addAnimal(new Position(1, 2));
        this.stockyardAnimals.addAnimal(new Position(2, 4));
        this.stockyardAnimals.addAnimal(new Position(3, 5));
        this.stockyardAnimals.addAnimal(new Position(5, 3));

        Assertions.assertTrue(this.stockyardAnimals.isFull());
        this.stockyardAnimals.removeAnimal();
        Assertions.assertEquals(3, this.stockyardAnimals.getSize());
        Assertions.assertFalse(this.stockyardAnimals.isFull());
        this.stockyardAnimals.removeAnimal();
        Assertions.assertEquals(2, this.stockyardAnimals.getSize());
        this.stockyardAnimals.removeAnimal();
        Assertions.assertEquals(1, this.stockyardAnimals.getSize());
        Assertions.assertFalse(this.stockyardAnimals.isEmpty());
        this.stockyardAnimals.removeAnimal();
        Assertions.assertTrue(this.stockyardAnimals.isEmpty());
        Assertions.assertEquals(0, this.stockyardAnimals.getSize());
        this.stockyardAnimals.removeAnimal();
        Assertions.assertTrue(this.stockyardAnimals.isEmpty());
        Assertions.assertEquals(0, this.stockyardAnimals.getSize());
    }
}
