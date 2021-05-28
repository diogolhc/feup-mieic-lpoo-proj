package controller.command.farm.stockyard;

import controller.command.Command;
import model.farm.Inventory;
import model.farm.building.Stockyard;
import model.farm.building.stockyard_state.NotProducing;
import model.farm.building.stockyard_state.Producing;
import model.farm.data.item.Crop;

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
            this.inventory.removeItem(this.stockyard.getLivestockType().getFoodCrop(), this.stockyard.getFeedQuantity());
        }
    }
}
