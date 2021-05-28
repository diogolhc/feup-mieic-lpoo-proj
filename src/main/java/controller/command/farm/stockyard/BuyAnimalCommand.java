package controller.command.farm.stockyard;

import controller.command.Command;
import model.farm.Farm;
import model.farm.building.Stockyard;

public class BuyAnimalCommand implements Command {
    private final Farm farm;
    private final Stockyard stockyard;

    public BuyAnimalCommand(Farm farm, Stockyard stockyard) {
        this.farm = farm;
        this.stockyard = stockyard;
    }

    @Override
    public void execute() {
        this.stockyard.addAnimal();
        this.farm.setCurrency(this.farm.getCurrency().subtract(this.stockyard.getLivestockType().getAnimalBuyPrice()));
    }
}
