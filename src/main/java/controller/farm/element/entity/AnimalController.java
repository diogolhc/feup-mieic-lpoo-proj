package controller.farm.element.entity;

import model.InGameTime;
import model.Position;
import model.farm.building.stockyard.StockyardAnimals;
import model.farm.entity.Animal;

public class AnimalController {
    private static final InGameTime MIN_NEXT_MOVEMENT = new InGameTime(1);
    private static final InGameTime MAX_NEXT_MOVEMENT = new InGameTime(7);
    private final StockyardAnimals stockyardAnimals;

    public AnimalController(StockyardAnimals stockyardAnimals) {
        this.stockyardAnimals = stockyardAnimals;
    }

    public void reactTimePassed(Animal animal, InGameTime elapsedTime) {
        animal.setIdleTime(animal.getIdleTime().subtract(elapsedTime));

        if (animal.getIdleTime().getMinute() <= 0) {
            InGameTime randomTime = InGameTime.getRandom(MIN_NEXT_MOVEMENT, MAX_NEXT_MOVEMENT);
            animal.setIdleTime(animal.getIdleTime().add(randomTime));

            this.move(animal, animal.getPosition().getRandomNeighbour());
        }
    }

    private void move(Animal animal, Position position) {
        if (this.stockyardAnimals.canAnimalMoveTo(position)) {
            animal.setPosition(position);
        }
    }
}
