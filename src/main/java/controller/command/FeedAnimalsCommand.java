package controller.command;

import model.farm.Inventory;
import model.farm.building.Stockyard;
import model.farm.building.crop_field_state.NotPlanted;
import model.farm.building.crop_field_state.Planted;
import model.farm.building.stockyard_state.NotProducing;
import model.farm.building.stockyard_state.Producing;
import model.farm.item.Crop;

public class FeedAnimalsCommand implements Command {
    private Inventory inventory;  //TODO This does not look good, command should not look for amounts
    private Stockyard stockyard;
    private Crop crop;

    public FeedAnimalsCommand(Stockyard stockyard, Inventory inventory, Crop crop) {
        this.stockyard = stockyard;
        this.inventory = inventory;
        this.crop = crop;
    }

    @Override
    public void execute() {
        if (this.stockyard.getState() instanceof NotProducing &&
                inventory.getAmount(stockyard.getLivestockType().getFoodCrop()) >=
                        stockyard.getLivestockType().getRequiredFood()*stockyard.getAnimals().size() ) {

            this.stockyard.setState(new Producing(this.stockyard, this.stockyard.getLivestockType().getProducedItem()));

            inventory.removeItem(stockyard.getLivestockType().getFoodCrop(),
                    stockyard.getLivestockType().getRequiredFood() * stockyard.getAnimals().size());
        }
    }
}
