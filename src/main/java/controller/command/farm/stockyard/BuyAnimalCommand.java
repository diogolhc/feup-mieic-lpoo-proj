package controller.command.farm.stockyard;

import controller.command.Command;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.StockyardAnimals;

public class BuyAnimalCommand implements Command {
    private final Farm farm;
    private final StockyardAnimals stockyardAnimals;
    private Currency price;

    public BuyAnimalCommand(Farm farm, StockyardAnimals stockyardAnimals, Currency price) {
        this.farm = farm;
        this.stockyardAnimals = stockyardAnimals;
        this.price = price;
    }

    @Override
    public void execute() {
        this.stockyardAnimals.addAnimal();
        this.farm.setCurrency(this.farm.getCurrency().subtract(price));
    }
}
