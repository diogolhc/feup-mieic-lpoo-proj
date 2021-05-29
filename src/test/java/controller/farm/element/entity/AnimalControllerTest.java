package controller.farm.element.entity;

import model.InGameTime;
import model.Position;
import model.farm.building.stockyard.StockyardAnimals;
import model.farm.entity.Animal;
import model.region.RectangleRegion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AnimalControllerTest {
    private StockyardAnimals animals;
    private AnimalController controller;

    @BeforeEach
    public void setUp() {
        animals = new StockyardAnimals(new RectangleRegion(new Position(0, 0), 5, 5), 5);

        animals.addAnimal();
        animals.addAnimal();
        animals.getList().get(0).setIdleTime(new InGameTime(10));
        animals.getList().get(1).setIdleTime(new InGameTime(3));
        controller = new AnimalController(animals);
    }

    @Test
    public void execute() {
        controller.reactTimePassed(animals.getList().get(0), new InGameTime(3));
        Assertions.assertEquals(animals.getList().get(0).getIdleTime(), new InGameTime(7));
        Assertions.assertNotEquals(animals.getList().get(1).getIdleTime(), new InGameTime(0));
        controller.reactTimePassed(animals.getList().get(0), new InGameTime(7));
        Assertions.assertNotEquals(animals.getList().get(0).getIdleTime(), new InGameTime(0));
    }
}
