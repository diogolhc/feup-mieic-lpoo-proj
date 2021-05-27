package controller.command;

import model.farm.Inventory;
import model.farm.building.Stockyard;
import model.farm.building.stockyard_state.NotProducing;
import model.farm.building.stockyard_state.ReadyToCollect;
import model.farm.data.item.AnimalProduct;

public class CollectStockyardCommand implements Command {
    private Inventory inventory;
    private Stockyard stockyard;

    public CollectStockyardCommand(Inventory inventory, Stockyard stockyard) {
        this.inventory = inventory;
        this.stockyard = stockyard;
    }

    @Override
    public void execute() {
        if (this.stockyard.getState() instanceof ReadyToCollect) {
            ReadyToCollect stockyardState = (ReadyToCollect) this.stockyard.getState();
            AnimalProduct producedItem = this.stockyard.getLivestockType().getProducedItem();

            this.inventory.storeItem(producedItem, stockyardState.getCollectAmount());
            this.stockyard.setState(new NotProducing());
        }
    }
}
