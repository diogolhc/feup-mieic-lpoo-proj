package controller.command;

import model.farm.Farm;
import model.farm.building.Stockyard;

public class SellAnimalCommand implements Command{
    private Farm farm;
    private Stockyard stockyard;

    public SellAnimalCommand(Farm farm, Stockyard stockyard) {
        this.farm = farm;
        this.stockyard = stockyard;
    }

    @Override
    public void execute() {
        this.stockyard.removeAnimal();
        this.farm.setCurrency(this.farm.getCurrency().add(this.stockyard.getLivestockType().getAnimalSellPrice()));
    }
}
