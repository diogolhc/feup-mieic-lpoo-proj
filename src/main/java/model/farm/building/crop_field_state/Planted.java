package model.farm.building.crop_field_state;

import model.InGameTime;
import model.farm.building.CropField;
import model.farm.item.Crop;

public class Planted implements CropFieldState {
    private final CropField cropField;
    private final Crop crop;
    private InGameTime timeRemaining;
    private double harvestAmount; // double so that small but in great quantity changes can have an effect

    public Planted(CropField cropField, Crop crop) {
        this.cropField = cropField;
        this.crop = crop;
        this.harvestAmount = this.crop.getBaseHarvestAmount();
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
            this.cropField.setState(new ReadyToHarvest(this.crop, this.harvestAmount));
        }
    }

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
