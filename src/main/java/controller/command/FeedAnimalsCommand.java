package controller.command;

import model.farm.Inventory;
import model.farm.building.Stockyard;
import model.farm.building.stockyard_state.NotProducing;
import model.farm.building.stockyard_state.Producing;
import model.farm.item.Crop;

public class FeedAnimalsCommand implements Command {
    private Inventory inventory;
    private Stockyard stockyard;
    private Crop crop;

    public FeedAnimalsCommand(Stockyard stockyard, Inventory inventory, Crop crop) {
        this.stockyard = stockyard;
        this.inventory = inventory;
        this.crop = crop;
    }

    @Override
    public void execute() {
        if (this.stockyard.getState() instanceof NotProducing) {
            this.stockyard.setState(new Producing(this.stockyard));
            this.inventory.removeItem(this.stockyard.getLivestockType().getFoodCrop(), this.stockyard.getFeedQuantity());
        }
    }
}
