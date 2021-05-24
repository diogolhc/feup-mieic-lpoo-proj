package model.farm.building.crop_field_state;

import model.InGameTime;
import model.farm.item.Crop;

public interface CropFieldState {
    InGameTime getRemainingTime();
    void setRemainingTime(InGameTime time);
    Crop getCrop();
    int getHarvestAmount();
}
