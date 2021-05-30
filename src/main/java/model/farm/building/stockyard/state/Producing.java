package model.farm.building.stockyard.state;

import gui.Color;
import model.InGameTime;
import model.farm.building.stockyard.Stockyard;

public class Producing implements StockyardState {
    private final Stockyard stockyard;
    private InGameTime timeRemaining;
    private double collectAmount; // double so that small but in great quantity changes can have an effect

    public Producing(Stockyard stockyard) {
        this.stockyard = stockyard;
        this.collectAmount = this.stockyard.getBaseProducedAmount();
        this.timeRemaining = stockyard.getLivestockType().getProducedItem().getProductionTime();
    }

    @Override
    public InGameTime getRemainingTime() {
        return this.timeRemaining;
    }

    @Override
    public void setRemainingTime(InGameTime time) {
        this.timeRemaining = time;
        if (this.timeRemaining.getMinute() <= 0) {
            this.stockyard.setState(new ReadyToCollect(this.stockyard, this.collectAmount));
        }
    }

    @Override
    public int getCollectAmount() {
        return (int) this.collectAmount;
    }

    @Override
    public void changeCollectAmount(double collectAmount) {
        this.collectAmount += collectAmount;
        if (this.collectAmount <= 0) {
            this.stockyard.setState(new NotProducing());
        }
    }

    @Override
    public Color getAnimalColor() {
        return new Color("#c96d82");
    }
}
