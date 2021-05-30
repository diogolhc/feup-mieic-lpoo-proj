package controller.farm.element.entity;

import model.InGameTime;
import model.Position;
import model.farm.building.stockyard.StockyardAnimals;
import model.farm.entity.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AnimalControllerTest {
    private StockyardAnimals stockyardAnimals;
    private AnimalController controller;
    private Animal animal1;
    private Animal animal2;

    @BeforeEach
    public void setUp() {
        this.stockyardAnimals = Mockito.mock(StockyardAnimals.class);

        this.animal1 = new Animal(new Position(2,2));
        this.animal2 = new Animal(new Position(4, 4));
        this.animal1.setIdleTime(new InGameTime(10));
        this.animal2.setIdleTime(new InGameTime(5));
        this.controller = new AnimalController(this.stockyardAnimals);
    }

    @Test
    public void canMove() {
        Mockito.when(this.stockyardAnimals.canAnimalMoveTo(Mockito.any())).thenReturn(true);
        Position animal1OldPosition = this.animal1.getPosition();
        Position animal2OldPosition = this.animal2.getPosition();

        this.controller.reactTimePassed(this.animal1, new InGameTime(3));
        Assertions.assertEquals(new InGameTime(7), this.animal1.getIdleTime());
        Assertions.assertEquals(new InGameTime(5), this.animal2.getIdleTime());
        Assertions.assertEquals(animal1OldPosition, this.animal1.getPosition());
        Assertions.assertEquals(animal2OldPosition, this.animal2.getPosition());

        this.controller.reactTimePassed(this.animal1, new InGameTime(5));
        this.controller.reactTimePassed(this.animal2, new InGameTime(5));
        Assertions.assertEquals(animal1OldPosition, this.animal1.getPosition());
        Assertions.assertEquals(new InGameTime(2), this.animal1.getIdleTime());
        Assertions.assertTrue(this.animal2.getIdleTime().getMinute() > 0);
        Mockito.verify(this.stockyardAnimals, Mockito.times(1)).canAnimalMoveTo(Mockito.any());
        Assertions.assertNotEquals(animal2OldPosition, this.animal2.getPosition());

        this.controller.reactTimePassed(this.animal1, new InGameTime(2));
        Assertions.assertNotEquals(animal1OldPosition, this.animal1.getPosition());
        Assertions.assertTrue(this.animal2.getIdleTime().getMinute() > 0);
        Mockito.verify(this.stockyardAnimals, Mockito.times(2)).canAnimalMoveTo(Mockito.any());
    }

    @Test
    public void cannotMove() {
        Mockito.when(this.stockyardAnimals.canAnimalMoveTo(Mockito.any())).thenReturn(false);
        Position animal1OldPosition = this.animal1.getPosition();
        Position animal2OldPosition = this.animal2.getPosition();

        this.controller.reactTimePassed(this.animal1, new InGameTime(3));
        Assertions.assertEquals(new InGameTime(7), this.animal1.getIdleTime());
        Assertions.assertEquals(new InGameTime(5), this.animal2.getIdleTime());
        Assertions.assertEquals(animal1OldPosition, this.animal1.getPosition());
        Assertions.assertEquals(animal2OldPosition, this.animal2.getPosition());

        this.controller.reactTimePassed(this.animal1, new InGameTime(5));
        this.controller.reactTimePassed(this.animal2, new InGameTime(5));
        Assertions.assertEquals(animal1OldPosition, this.animal1.getPosition());
        Assertions.assertEquals(new InGameTime(2), this.animal1.getIdleTime());
        Assertions.assertTrue(this.animal2.getIdleTime().getMinute() > 0);
        Mockito.verify(this.stockyardAnimals, Mockito.times(1)).canAnimalMoveTo(Mockito.any());
        Assertions.assertEquals(animal2OldPosition, this.animal2.getPosition());

        this.controller.reactTimePassed(this.animal1, new InGameTime(2));
        Assertions.assertEquals(animal1OldPosition, this.animal1.getPosition());
        Assertions.assertTrue(this.animal1.getIdleTime().getMinute() > 0);
        Mockito.verify(this.stockyardAnimals, Mockito.times(2)).canAnimalMoveTo(Mockito.any());
    }
}
