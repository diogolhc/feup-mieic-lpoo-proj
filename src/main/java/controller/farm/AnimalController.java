package controller.farm;

import model.Position;
import model.farm.Animals.Animal;
import model.farm.building.Stockyard;

public class AnimalController {
    private static int lastMovement = 0;

    public void resetLastMovement() {
        if (lastMovement > 100) {
            this.lastMovement = 0;
        }
    }

    public void reactTimePassed(Stockyard<? extends Animal> stockyard) {
        // TODO Make animals of stockyard move and decrease its Hunger
        if (lastMovement >= 100) {
            for (Animal animal : stockyard.getAnimals()) {
                if (!animal.isDead()) {
                    Position newPosition = animal.getPosition().getRandomNeighbour();
                    if (stockyard.isTraversable(newPosition)) {
                        animal.setPosition(newPosition);
                    }
                }
            }
        }
        lastMovement++;

        for (Animal animal : stockyard.getAnimals()) {
            if (!animal.isDead()) {
                animal.decreaseHunger();
            }
        }
    }
}
