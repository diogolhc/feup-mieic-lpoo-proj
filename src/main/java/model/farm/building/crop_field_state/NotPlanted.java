package model.farm.building.crop_field_state;

import model.InGameTime;
import model.farm.item.Crop;

public class NotPlanted implements CropFieldState {
    @Override
    public InGameTime getRemainingTime() {
        return new InGameTime(0);
    }

    @Override
    public void setRemainingTime(InGameTime time) {}

    @Override
    public Crop getCrop() {
        return Crop.NO_CROP;
    }

    @Override
    public int getHarvestAmount() {
        return 0;
    }
}