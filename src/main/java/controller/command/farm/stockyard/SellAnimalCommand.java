package controller.command.farm.stockyard;

import controller.command.Command;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.Wallet;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.StockyardAnimals;

public class SellAnimalCommand implements Command {
    private final Wallet wallet;
    private final StockyardAnimals stockyardAnimals;
    private final Currency sellPrice;

    public SellAnimalCommand(Wallet wallet, StockyardAnimals stockyardAnimals, Currency sellPrice) {
        this.wallet = wallet;
        this.stockyardAnimals = stockyardAnimals;
        this.sellPrice = sellPrice;
    }

    @Override
    public void execute() {
        if (!this.stockyardAnimals.isEmpty()) {
            this.stockyardAnimals.removeAnimal();
            this.wallet.receive(this.sellPrice);
        }
    }
}
