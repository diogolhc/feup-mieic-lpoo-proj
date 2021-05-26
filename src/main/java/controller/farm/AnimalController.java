package controller.farm;

import model.InGameTime;
import model.Position;
import model.farm.Animal;
import model.farm.building.Stockyard;

public class AnimalController {
    private static int lastMovement = 0;

    public void reset() {
        lastMovement = lastMovement > 100 ? 0 : lastMovement;
    }

    public void reactTimePassed(Stockyard stockyard, InGameTime elapsedTime) {
        // TODO use elapsed time for more intelligent movement
        // TODO stockyard MUST NOT be passed to this function, send me a message if you need help
        if (lastMovement >= 100) {
            for (Animal animal : stockyard.getAnimals()) {
                Position newPosition = animal.getPosition().getRandomNeighbour();
                if (stockyard.getAnimalsRegion().contains(newPosition) && stockyard.emptyPosition(newPosition)) {
                    animal.setPosition(newPosition);
                }
            }
        }
        lastMovement++;
    }
}
