package model.farm.building.crop_field.state;

import model.InGameTime;
import model.farm.building.crop_field.CropField;
import model.farm.data.item.Crop;

public class Planted implements CropFieldState {
    private final CropField cropField;
    private final Crop crop;
    private InGameTime timeRemaining;
    private double harvestAmount; // double so that fractional changes may add up and have an effect

    public Planted(CropField cropField, Crop crop) {
        this.cropField = cropField;
        this.crop = crop;
        this.harvestAmount = this.crop.getBaseHarvestAmount();
        this.timeRemaining = this.crop.getGrowTime();
    }

    @Override
    public InGameTime getRemainingTime() {
        return timeRemaining;
    }

    @Override
    public void setRemainingTime(InGameTime time) {
        this.timeRemaining = time;
        if (this.timeRemaining.getMinute() <= 0) {
            this.cropField.setState(new ReadyToHarvest(this.cropField, this.crop, this.harvestAmount));
        }
    }

    @Override
    public Crop getCrop() {
        return this.crop;
    }

    @Override
    public int getHarvestAmount() {
        return (int) this.harvestAmount;
    }

    @Override
    public void changeHarvestAmount(double harvestAmount) {
        this.harvestAmount += harvestAmount;
        if (this.harvestAmount <= 0) {
            this.cropField.setState(new NotPlanted());
        }
    }
}
