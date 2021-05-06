package controller.command;

import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;

public class RemoveCropCommand implements Command {
    private CropField cropField;

    public RemoveCropCommand(CropField cropField) {
        this.cropField = cropField;
    }

    @Override
    public void execute() {
        if (this.cropField.getState() instanceof Planted) {
            this.cropField.setState(new NotPlanted());
        }
    }
}
