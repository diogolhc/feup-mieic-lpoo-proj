package controller.command.farm.stockyard;

import controller.command.Command;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.StockyardAnimals;

public class SellAnimalCommand implements Command {
    private final Farm farm;
    private final StockyardAnimals stockyardAnimals;
    private Currency sellPrice;

    public SellAnimalCommand(Farm farm, StockyardAnimals stockyardAnimals, Currency sellPrice) {
        this.farm = farm;
        this.stockyardAnimals = stockyardAnimals;
        this.sellPrice = sellPrice;
    }

    @Override
    public void execute() {
        this.stockyardAnimals.removeAnimal();
        this.farm.setCurrency(this.farm.getCurrency().add(this.sellPrice));
    }
}
