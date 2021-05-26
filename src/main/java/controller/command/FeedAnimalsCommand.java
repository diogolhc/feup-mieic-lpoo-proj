package controller.command;

import model.farm.building.Stockyard;
import model.farm.building.crop_field_state.NotPlanted;
import model.farm.building.crop_field_state.Planted;
import model.farm.building.stockyard_state.NotProducing;
import model.farm.building.stockyard_state.Producing;
import model.farm.item.Crop;

public class FeedAnimalsCommand implements Command {
    private Stockyard stockyard;
    private Crop crop;

    public FeedAnimalsCommand(Stockyard stockyard, Crop crop) {
        this.stockyard = stockyard;
        this.crop = crop;
    }

    @Override
    public void execute() {
        if (this.stockyard.getState() instanceof NotProducing) {
            this.stockyard.setState(new Producing(this.stockyard, this.stockyard.getState().getProduct()));
        }
    }
}
