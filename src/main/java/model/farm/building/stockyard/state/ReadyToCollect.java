package model.farm.building.stockyard.state;

import gui.Color;
import model.InGameTime;
import model.farm.building.stockyard.Stockyard;

public class ReadyToCollect implements StockyardState{
    private Stockyard stockyard;
    private double collectAmount; // double so that small but in great quantity changes can have an effect

    public ReadyToCollect(Stockyard stockyard, double collectAmount) {
        this.stockyard = stockyard;
        this.collectAmount = collectAmount;
    }

    @Override
    public void changeCollectAmount(double collectAmount) {
        // when readyToCollect only bad effects take place
        // what would be a good effect while producing
        // will spoil the product over time
        if (collectAmount > 0) {
            collectAmount *= -1;
        }
        this.collectAmount += collectAmount;
        if (this.collectAmount <= 0) {
            this.stockyard.setState(new NotProducing());
        }
    }

    @Override
    public int getCollectAmount() {
        return (int) this.collectAmount;
    }

    @Override
    public InGameTime getRemainingTime() {
        return new InGameTime(0);
    }

    @Override
    public void setRemainingTime(InGameTime time) {}

    @Override
    public Color getAnimalColor() {
        return new Color("#298a36");
    }
}
