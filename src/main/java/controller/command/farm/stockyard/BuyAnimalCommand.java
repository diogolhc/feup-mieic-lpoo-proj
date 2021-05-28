package controller.command.farm.stockyard;

import controller.command.Command;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.Wallet;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.StockyardAnimals;

public class BuyAnimalCommand implements Command {
    private final Wallet wallet;
    private final StockyardAnimals stockyardAnimals;
    private final Currency price;

    public BuyAnimalCommand(Wallet wallet, StockyardAnimals stockyardAnimals, Currency price) {
        this.wallet = wallet;
        this.stockyardAnimals = stockyardAnimals;
        this.price = price;
    }

    @Override
    public void execute() {
        if (!this.stockyardAnimals.isFull()) {
            this.stockyardAnimals.addAnimal();
            this.wallet.spend(price);
        }
    }
}
