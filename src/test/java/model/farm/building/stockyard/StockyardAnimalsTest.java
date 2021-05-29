package model.farm.building.stockyard;

import model.Position;
import model.region.RectangleRegion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class StockyardAnimalsTest {
    StockyardAnimals stockyardAnimals;

    @BeforeEach
    void setUp() {
        RectangleRegion rectangleRegion = Mockito.mock(RectangleRegion.class);
        Mockito.when(rectangleRegion.getHeight()).thenReturn(10);
        Mockito.when(rectangleRegion.getWidth()).thenReturn(10);
        Mockito.when(rectangleRegion.getTopLeftPosition()).thenReturn(new Position(0,0));

        Mockito.doAnswer( invocation -> {
            Position position = invocation.getArgument(0);
            return position.getX() >= 0 && position.getX() < 10 &&
                    position.getY() >= 0 && position.getY() < 10;
        }).when(rectangleRegion).contains(Mockito.mock(Position.class));


        stockyardAnimals = new StockyardAnimals(rectangleRegion, 4);
    }

    @Test
    void addAnimal() {
        stockyardAnimals.addAnimal();
        Assertions.assertEquals(1, stockyardAnimals.getSize());
    }

    @Test
    void addSeveralAnimals() {
        Assertions.assertTrue(stockyardAnimals.isEmpty());
        stockyardAnimals.addAnimal();
        Assertions.assertEquals(1, stockyardAnimals.getSize());
        stockyardAnimals.addAnimal();
        Assertions.assertEquals(2, stockyardAnimals.getSize());
        stockyardAnimals.addAnimal();
        Assertions.assertEquals(3, stockyardAnimals.getSize());
        stockyardAnimals.addAnimal();
        Assertions.assertEquals(4, stockyardAnimals.getSize());
        stockyardAnimals.addAnimal();
        Assertions.assertEquals(4, stockyardAnimals.getSize());
        Assertions.assertTrue(stockyardAnimals.isFull());
    }

    @Test
    void isAnimalAt() {
        stockyardAnimals.addAnimal(new Position(0, 0));
        Assertions.assertTrue(stockyardAnimals.isAnimalAt(new Position(0,0)));
    }

    @Test
    void canMoveTo() {
        stockyardAnimals.addAnimal(new Position(0,0));
        Assertions.assertFalse(stockyardAnimals.canAnimalMoveTo(new Position(0,0)));
    }

    @Test
    void removeAnimal() {
        stockyardAnimals.addAnimal();
        stockyardAnimals.removeAnimal();
        Assertions.assertEquals(0, stockyardAnimals.getSize());
    }

    @Test
    void removeAnimalZero() {
        stockyardAnimals.removeAnimal();
        Assertions.assertEquals(0, stockyardAnimals.getSize());
    }

}
