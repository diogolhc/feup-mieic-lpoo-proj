package model.farm.building.crop_field_state;

import model.InGameTime;
import model.farm.item.Crop;

public class ReadyToHarvest implements CropFieldState {
    private Crop crop;
    private int harvestAmount;

    public ReadyToHarvest(Crop crop, int harvestAmount) {
        this.crop = crop;
        this.harvestAmount = harvestAmount;
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

    @Override
    public int getHarvestAmount() {
        return this.harvestAmount;
    }
}
