package model.farm.building.crop_field.state;

import model.InGameTime;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.crop.Crop;

public class Planted implements CropFieldState {
    private final CropField cropField;
    private final Crop crop;
    private InGameTime timeRemaining;

    public Planted(CropField cropField, Crop crop) {
        this.cropField = cropField;
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
        if (this.timeRemaining.getMinute() <= 0) {
            this.cropField.setState(new ReadyToHarvest(this.crop));
        }
    }

    @Override
    public Crop getCrop() {
        return this.crop;
    }
}
