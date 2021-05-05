package model.farm.building.crop_field.state;

import model.InGameTime;
import model.farm.building.crop_field.crop.Crop;
import model.farm.building.crop_field.crop.NoCrop;

public class NotPlanted implements CropFieldState {
    @Override
    public InGameTime getRemainingTime() {
        return new InGameTime(0);
    }

    @Override
    public void setRemainingTime(InGameTime time) {}

    @Override
    public Crop getCrop() {
        return new NoCrop();
    }
}
