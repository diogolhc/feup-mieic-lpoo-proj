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
    public Color getColor() {
        return new Color("#b0b0b0");
    }

    @Override
    public void changeProductAmount(double productAmount) {}

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

