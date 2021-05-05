package model.farm.building.crop_field.state;

import model.IngameTime;
import model.farm.building.crop_field.crop.Crop;

public class Planted implements CropFieldState {
    private Crop crop;
    private IngameTime timeRemaining;

    public Planted(Crop crop) {
        this.crop = crop;
        timeRemaining = this.crop.getGrowTime();
    }

    @Override
    public IngameTime getRemainingTime() {
        return timeRemaining;
    }

    @Override
    public void setRemainingTime(IngameTime time) {
        this.timeRemaining = time;
    }

    @Override
    public Crop getCrop() {
        return this.crop;
    }
}
