package model.farm.building.crop_field.state;

import model.ChronologicalTime;
import model.farm.building.crop_field.crop.Crop;

public class Planted implements CropFieldState {
    private Crop crop;
    private ChronologicalTime timeRemaining;

    public Planted(Crop crop) {
        this.crop = crop;
        timeRemaining = this.crop.getGrowTime();
    }

    @Override
    public ChronologicalTime getRemainingTime() {
        return timeRemaining;
    }

    @Override
    public Crop getCrop() {
        return this.crop;
    }
}
