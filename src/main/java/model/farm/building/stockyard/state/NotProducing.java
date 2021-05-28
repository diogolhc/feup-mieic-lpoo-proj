package model.farm.building.stockyard.state;

import gui.Color;
import model.InGameTime;

public class NotProducing implements StockyardState {
    @Override
    public InGameTime getRemainingTime() {
        return new InGameTime(0);
    }

    @Override
    public void setRemainingTime(InGameTime time) {}

    @Override
    public int getCollectAmount() {
        return 0;
    }

    @Override
    public void changeCollectAmount(double collectAmount) {}

    @Override
    public Color getAnimalColor() {
        return new Color("#223366");
    }
}
