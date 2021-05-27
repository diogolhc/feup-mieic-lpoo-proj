package model.farm.building.crop_field_state;

import model.InGameTime;
import model.farm.data.item.Crop;

import java.io.Serializable;

public interface CropFieldState extends Serializable {
    InGameTime getRemainingTime();
    void setRemainingTime(InGameTime time);
    int getHarvestAmount();
    void changeHarvestAmount(double harvestAmount);
    Crop getCrop();
}
