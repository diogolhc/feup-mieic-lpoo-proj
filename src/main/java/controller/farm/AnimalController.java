package controller.farm;

import model.InGameTime;
import model.Position;
import model.farm.Animal;
import model.farm.Farm;
import model.farm.Weather;
import model.farm.building.Stockyard;

import java.util.Map;

public class AnimalController {
    private static final InGameTime MIN_NEXT_MOVEMENT = new InGameTime(1);
    private static final InGameTime MAX_NEXT_MOVEMENT = new InGameTime(7);
    private Stockyard stockyard;

    public AnimalController(Stockyard stockyard) {
        this.stockyard = stockyard;
    }

    public void reactTimePassed(Animal animal, InGameTime elapsedTime) {
        animal.setIdleTime(animal.getIdleTime().subtract(elapsedTime));

        if (animal.getIdleTime().getMinute() <= 0) {
            InGameTime randomTime = InGameTime.getRandom(MIN_NEXT_MOVEMENT, MAX_NEXT_MOVEMENT);
            animal.setIdleTime(animal.getIdleTime().add(randomTime));

            move(animal, animal.getPosition().getRandomNeighbour());
        }
    }

    private void move(Animal animal, Position position) {
        if (stockyard.getAnimalsRegion().contains(position) && stockyard.emptyPosition(position)) {
            animal.setPosition(position);
        }
    }
}
