package model.farm.building.crop_field.state;

import model.InGameTime;
import model.farm.data.item.Crop;

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

    @Override
    public void changeHarvestAmount(double harvestAmount) {}
}
