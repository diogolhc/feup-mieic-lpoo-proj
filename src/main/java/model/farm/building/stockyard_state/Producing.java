package model.farm.building.stockyard_state;

import gui.Color;
import model.InGameTime;
import model.farm.building.Stockyard;
import model.farm.item.AnimalProduct;

public class Producing implements StockyardState {
    private final Stockyard stockyard;
    private InGameTime timeRemaining;
    private double collectAmount; // double so that small but in great quantity changes can have an effect

    public Producing(Stockyard stockyard) {
        this.stockyard = stockyard;
        AnimalProduct product = stockyard.getLivestockType().getProducedItem();
        this.collectAmount = product.getBaseProducedAmount() * this.stockyard.getAnimals().size();
        this.timeRemaining = product.getProductionTime();
    }

    @Override
    public InGameTime getRemainingTime() {
        return this.timeRemaining;
    }

    @Override
    public void setRemainingTime(InGameTime time) {
        this.timeRemaining = time;
        if (this.timeRemaining.getMinute() <= 0) {
            this.stockyard.setState(new ReadyToCollect(stockyard, this.collectAmount));
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
