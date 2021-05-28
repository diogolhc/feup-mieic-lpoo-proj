package controller.command.farm.stockyard;

import controller.command.Command;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.building.stockyard.state.Producing;

public class StopProducingStockyardCommand implements Command {
    private final Stockyard stockyard;

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
