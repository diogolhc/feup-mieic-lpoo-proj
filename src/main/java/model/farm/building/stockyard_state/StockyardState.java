package model.farm.building.stockyard_state;

import gui.Color;
import model.InGameTime;
import model.farm.item.AnimalProduct;

import java.io.Serializable;

public interface StockyardState extends Serializable {
    InGameTime getRemainingTime();
    void setRemainingTime(InGameTime time);
    int getCollectAmount();
    void changeCollectAmount(double collectAmount);
    Color getAnimalColor();
}
