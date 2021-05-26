package model.farm.building.stockyard_state;

import gui.Color;
import model.InGameTime;
import model.farm.item.AnimalProduct;

public class ReadyToCollect implements StockyardState{
    private final AnimalProduct animalProduct;
    private double productAmount;

    public ReadyToCollect(AnimalProduct crop, double productAmount) {
        this.animalProduct = crop;
        this.productAmount = productAmount;
    }

    @Override
    public AnimalProduct getProduct() {
        return this.animalProduct;
    }

    @Override
    public char getChar() {
        return this.animalProduct.getName().charAt(0) ;
    }

    @Override
    public void changeProductAmount(double productAmount) {
        this.productAmount += productAmount;
        this.productAmount = this.productAmount < 0 ? 0 : this.productAmount;
    }

    @Override
    public int getProductAmount() {
        return (int) this.productAmount;
    }

    @Override
    public InGameTime getRemainingTime() {
        return new InGameTime(0);
    }

    @Override
    public void setRemainingTime(InGameTime time) {}

}

