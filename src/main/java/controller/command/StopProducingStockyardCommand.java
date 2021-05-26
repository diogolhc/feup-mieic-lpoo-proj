package controller.command;

import model.farm.building.Stockyard;
import model.farm.building.stockyard_state.NotProducing;
import model.farm.building.stockyard_state.Producing;

public class StopProducingStockyardCommand implements Command {
    private Stockyard stockyard;

    public StopProducingStockyardCommand(Stockyard stockyard) {
        this.stockyard = stockyard;
    }

    @Override
    public void execute() {
        if (this.stockyard.getState() instanceof Producing) {
            this.stockyard.setState(new NotProducing());
        }
    }
}
