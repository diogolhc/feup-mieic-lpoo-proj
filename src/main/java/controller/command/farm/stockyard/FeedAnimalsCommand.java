package controller.command.farm.stockyard;

import controller.command.Command;
import model.farm.Inventory;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.building.stockyard.state.Producing;

public class FeedAnimalsCommand implements Command {
    private final Inventory inventory;
    private final Stockyard stockyard;

    public FeedAnimalsCommand(Stockyard stockyard, Inventory inventory) {
        this.stockyard = stockyard;
        this.inventory = inventory;
    }

    @Override
    public void execute() {
        if (this.stockyard.getState() instanceof NotProducing) {
            this.stockyard.setState(new Producing(this.stockyard));
            this.inventory.removeItem(this.stockyard.getLivestockType().getFoodCrop(), this.stockyard.getRequiredFood());
        }
    }
}
