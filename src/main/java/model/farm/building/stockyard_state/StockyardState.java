package model.farm.building.stockyard_state;

import model.InGameTime;
import model.farm.item.AnimalProduct;

import java.io.Serializable;

public interface StockyardState extends Serializable {
    InGameTime getRemainingTime();
    void setRemainingTime(InGameTime time);
    AnimalProduct getProduct();
    int getProductAmount();
    void changeProductAmount(double productAmount);
}
