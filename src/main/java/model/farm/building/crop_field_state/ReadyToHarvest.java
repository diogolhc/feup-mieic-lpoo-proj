package model.farm.building.crop_field_state;

import model.InGameTime;
import model.farm.building.CropField;
import model.farm.data.item.Crop;

public class ReadyToHarvest implements CropFieldState {
    private CropField cropField;
    private Crop crop;
    private double harvestAmount; // double so that small but in great quantity changes can have an effect

    public ReadyToHarvest(CropField cropField, Crop crop, double harvestAmount) {
        this.cropField = cropField;
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
        return (int) this.harvestAmount;
    }

    @Override
    public void changeHarvestAmount(double harvestAmount) {
        // when readyToHarvest only bad effects take place
        // what would be a good effect while crop was in growth stage
        // will rot it when ready to harvest
        if (harvestAmount > 0) {
            harvestAmount *= -1;
        }

        this.harvestAmount += harvestAmount;
        if (this.harvestAmount <= 0) {
            this.cropField.setState(new NotPlanted());
        }
    }
}
