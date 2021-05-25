package model.farm.building.crop_field_state;

import model.InGameTime;
import model.farm.item.Crop;

public class ReadyToHarvest implements CropFieldState {
    private Crop crop;
    private double harvestAmount; // double so that small but in great quantity changes can have an effect

    public ReadyToHarvest(Crop crop, double harvestAmount) {
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
        return (int)this.harvestAmount;
    }

    @Override
    public void changeHarvestAmount(double harvestAmount) {
        this.harvestAmount += harvestAmount;
        this.harvestAmount = this.harvestAmount < 0 ? 0 : this.harvestAmount;
    }
}
