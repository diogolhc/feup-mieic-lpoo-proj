package model.farm.building.stockyard_state;

import gui.Color;
import model.InGameTime;
import model.farm.building.Stockyard;
import model.farm.building.crop_field_state.ReadyToHarvest;
import model.farm.item.AnimalProduct;

public class Producing implements StockyardState {
    private final Stockyard stockyard;
    private final AnimalProduct animalProduct;
    private InGameTime timeRemaining;
    private double productAmount; // double so that small but in great quantity changes can have an effect

    public Producing(Stockyard stockyard, AnimalProduct animalProduct) {
        this.stockyard = stockyard;
        this.animalProduct = animalProduct;
        this.productAmount = this.animalProduct.getBaseProducedAmount();
        timeRemaining = this.animalProduct.getProductionTime();
    }

    @Override
    public InGameTime getRemainingTime() {
        return timeRemaining;
    }

    @Override
    public void setRemainingTime(InGameTime time) {
        this.timeRemaining = time;
        if (this.timeRemaining.getMinute() <= 0) {
            this.stockyard.setState(new ReadyToCollect(this.animalProduct, this.productAmount));
        }
    }

    @Override
    public char getChar() {
        return (char) (this.animalProduct.getName().charAt(0) + 32);
        //ascii conversion
    }

    @Override
    public AnimalProduct getProduct() {
        return this.animalProduct;
    }

    @Override
    public int getProductAmount() {
        return (int)this.productAmount;
    }

    @Override
    public void changeProductAmount(double productAmount) {
        this.productAmount += productAmount;
        this.productAmount = this.productAmount < 0 ? 0 : this.productAmount;
    }

}
