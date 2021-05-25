package controller.farm.building;

import controller.GameController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import model.InGameTime;
import model.Position;
import model.farm.Animals.Animal;
import model.farm.Farm;
import model.farm.building.Stockyard;
import model.farm.building.CropField;

public class StockyardController extends BuildingController<Stockyard> {
    private final GameController controller;
    private Farm farm;
    private static int lastMovement = 0;

    public StockyardController(GameController controller, Farm farm) {
        this.controller = controller;
    }

    public void resetLastMovement() {
        if (lastMovement > 100) {
            this.lastMovement = 0;
        }
    }

    @Override
    public Command getInteractionCommand(Stockyard stockyard) {
        // TODO
        System.out.println("Stockyard interaction not implemented yet");
        return new NoOperationCommand();
    }

    public void reactTimePassed(Stockyard<? extends Animal> stockyard) {
        // TODO Make animals of stockyard move and increse its Hunger
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
    }
}
