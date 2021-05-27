package controller.command;

import model.farm.Farm;
import model.farm.building.CropField;
import model.farm.data.item.Crop;
import model.farm.building.crop_field_state.NotPlanted;
import model.farm.building.crop_field_state.Planted;

public class PlantCropCommand implements Command {
    private Farm farm;
    private CropField cropField;
    private Crop crop;

    public PlantCropCommand(Farm farm, CropField cropField, Crop crop) {
        this.farm = farm;
        this.cropField = cropField;
        this.crop = crop;
    }

    @Override
    public void execute() {
        if (this.cropField.getState() instanceof NotPlanted) {
            this.cropField.setState(new Planted(cropField, this.crop));
            this.farm.setCurrency(this.farm.getCurrency().subtract(this.crop.getPlantPrice()));
        }
    }
}
