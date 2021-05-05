package model.farm.building.crop_field.state;

import model.InGameTime;
import model.farm.building.crop_field.crop.Crop;

public class Planted implements CropFieldState {
    private Crop crop;
    private InGameTime timeRemaining;

    public Planted(Crop crop) {
        this.crop = crop;
        timeRemaining = this.crop.getGrowTime();
    }

    @Override
    public InGameTime getRemainingTime() {
        return timeRemaining;
    }

    @Override
    public void setRemainingTime(InGameTime time) {
        this.timeRemaining = time;
    }

    @Override
    public Crop getCrop() {
        return this.crop;
    }
}
