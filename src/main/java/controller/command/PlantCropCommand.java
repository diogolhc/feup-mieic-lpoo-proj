package controller.command;

import model.farm.building.CropField;
import model.farm.item.Crop;
import model.farm.building.crop_field_state.NotPlanted;
import model.farm.building.crop_field_state.Planted;

public class PlantCropCommand implements Command {
    private CropField cropField;
    private Crop crop;

    public PlantCropCommand(CropField cropField, Crop crop) {
        this.cropField = cropField;
        this.crop = crop;
    }

    @Override
    public void execute() {
        if (this.cropField.getState() instanceof NotPlanted) {
            this.cropField.setState(new Planted(cropField, this.crop));
        }
    }
}
