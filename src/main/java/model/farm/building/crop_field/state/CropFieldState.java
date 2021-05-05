package model.farm.building.crop_field.state;

import model.IngameTime;
import model.farm.building.crop_field.crop.Crop;

public interface CropFieldState {
    IngameTime getRemainingTime();
    void setRemainingTime(IngameTime time);
    Crop getCrop();
}
