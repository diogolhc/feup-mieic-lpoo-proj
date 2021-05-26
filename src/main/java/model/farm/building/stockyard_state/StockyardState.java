package model.farm.building.stockyard_state;

import model.InGameTime;
import model.farm.item.AnimalProduct;

public interface StockyardState {
    InGameTime getRemainingTime();
    void setRemainingTime(InGameTime time);
    AnimalProduct getProduct();
    int getProductAmount();
    void changeProductAmount(double productAmount);
}
