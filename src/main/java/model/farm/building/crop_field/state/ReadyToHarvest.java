package model.farm.building.crop_field.state;

import model.InGameTime;
import model.farm.building.crop_field.crop.Crop;

public class ReadyToHarvest implements CropFieldState {
    private Crop crop;

    public ReadyToHarvest(Crop crop) {
        this.crop = crop;
    }

    @Override
    public InGameTime getRemainingTime() {
        return new InGameTime(0);
    }

    @Override
    public void setRemainingTime(InGameTime time) {}

    @Override
    public Crop getCrop() {
        return this.crop;
    }
}
