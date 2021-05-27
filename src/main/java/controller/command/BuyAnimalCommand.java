package controller.command;

import model.farm.Farm;
import model.farm.building.Stockyard;
import model.farm.building.stockyard_state.NotProducing;

public class BuyAnimalCommand implements Command {
    private Farm farm;
    private Stockyard stockyard;

    public BuyAnimalCommand(Farm farm, Stockyard stockyard) {
        this.farm = farm;
        this.stockyard = stockyard;
    }

    @Override
    public void execute() {
        if (this.farm.getCurrency().canBuy(this.stockyard.getLivestockType().getAnimalBuyPrice()) &&
                this.stockyard.canAddAnimal() ) {
            this.farm.setCurrency(this.farm.getCurrency().subtract(this.stockyard.getLivestockType().getAnimalBuyPrice()));
            this.stockyard.addAnimal();
        }
    }
}
