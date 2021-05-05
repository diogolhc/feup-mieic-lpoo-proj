package model.farm.building.crop_field.state;

import model.IngameTime;
import model.farm.building.crop_field.crop.Crop;

public class ReadyToHarvest implements CropFieldState {
    private Crop crop;

    public ReadyToHarvest(Crop crop) {
        this.crop = crop;
    }

    @Override
    public IngameTime getRemainingTime() {
        return new IngameTime(0);
    }

    @Override
    public void setRemainingTime(IngameTime time) {}

    @Override
    public Crop getCrop() {
        return this.crop;
    }
}
