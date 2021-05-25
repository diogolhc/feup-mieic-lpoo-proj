package controller.farm.building;

import controller.GameController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import controller.farm.AnimalController;
import model.InGameTime;
import model.Position;
import model.farm.Animals.Animal;
import model.farm.Animals.Hunger;
import model.farm.Farm;
import model.farm.building.Stockyard;
import model.farm.building.CropField;

public class StockyardController extends BuildingController<Stockyard> {
    private final GameController controller;
    private final AnimalController animalController;
    private Farm farm;

    public StockyardController(GameController controller, Farm farm) {
        this.controller = controller;
        this.animalController = new AnimalController();
    }

    public void resetLastMovement() {
        animalController.resetLastMovement();
    }

    @Override
    public Command getInteractionCommand(Stockyard stockyard) {
        // TODO
        System.out.println("Stockyard interaction not implemented yet");
        return new NoOperationCommand();
    }

    public void reactTimePassed(Stockyard<? extends Animal> stockyard) {
        // TODO Make animals of stockyard move and decrease its Hunger
        animalController.reactTimePassed(stockyard);

    }
}
