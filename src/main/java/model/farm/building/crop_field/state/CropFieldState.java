package model.farm.building.crop_field.state;

import model.InGameTime;
import model.farm.building.crop_field.crop.Crop;

public interface CropFieldState {
    InGameTime getRemainingTime();
    void setRemainingTime(InGameTime time);
    Crop getCrop();
}
