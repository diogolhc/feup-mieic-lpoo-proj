package controller.farm;

import model.Position;
import model.farm.Animals.Animal;
import model.farm.building.Stockyard;

public class AnimalController {
    private static int lastMovement = 0;
    private static int lastHungerDecrease = 0;

    public void reset() {
        lastMovement = lastMovement > 100 ? 0 : lastMovement;
        lastHungerDecrease = lastHungerDecrease > 20 ? 0 : lastHungerDecrease;
    }

    public void reactTimePassed(Stockyard<? extends Animal> stockyard, long elapsedTime) {
        // TODO Make animals of stockyard move and decrease its Hunger
        if (lastMovement >= 100) {
            for (Animal animal : stockyard.getAnimals()) {
                if (!animal.isDead()) {
                    Position newPosition = animal.getPosition().getRandomNeighbour();
                    if (stockyard.isTraversable(newPosition) && stockyard.emptyPosition(newPosition)) {
                        animal.setPosition(newPosition);
                    }
                }
            }
        }
        lastMovement++;

        if (lastHungerDecrease >= 20) {
            for (Animal animal : stockyard.getAnimals()) {
                if (!animal.isDead()) {
                    animal.decreaseHunger();
                }
            }
        }
    }
}
