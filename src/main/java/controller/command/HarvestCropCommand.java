package controller.command;

import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.ReadyToHarvest;

public class HarvestCropCommand implements Command {
    private CropField cropField;

    public HarvestCropCommand(CropField cropField) {
        this.cropField = cropField;
    }

    @Override
    public void execute() {
        if (this.cropField.getState() instanceof ReadyToHarvest) {
            this.cropField.setState(new NotPlanted(this.cropField));
        }
    }
}
