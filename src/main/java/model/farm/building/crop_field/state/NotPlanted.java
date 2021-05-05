package model.farm.building.crop_field.state;

import model.IngameTime;
import model.farm.building.crop_field.crop.Crop;
import model.farm.building.crop_field.crop.NoCrop;

public class NotPlanted implements CropFieldState {
    @Override
    public IngameTime getRemainingTime() {
        return new IngameTime(0);
    }

    @Override
    public void setRemainingTime(IngameTime time) {}

    @Override
    public Crop getCrop() {
        return new NoCrop();
    }
}
