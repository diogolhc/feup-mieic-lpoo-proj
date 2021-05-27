package model.farm.building.stockyard_state;

import gui.Color;
import model.InGameTime;
import model.farm.Currency;
import model.farm.item.AnimalProduct;
import model.farm.item.Crop;

public class NotProducing implements StockyardState {

    @Override
    public InGameTime getRemainingTime() {
        return new InGameTime(0);
    }

    @Override
    public void setRemainingTime(InGameTime time) {}

    @Override
    public AnimalProduct getProduct() {
        return new AnimalProduct(" ");
    }

    @Override
    public Color getColor() {
        return new Color("#223366");
    }

    @Override
    public int getProductAmount() {
        return 0;
    }

    @Override
    public void changeProductAmount(double productAmount) {}
}
