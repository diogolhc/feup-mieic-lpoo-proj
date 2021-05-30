package controller.command.farm.crop_field;

import controller.command.Command;
import model.farm.Wallet;
import model.farm.building.crop_field.CropField;
import model.farm.data.item.Crop;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;

public class PlantCropCommand implements Command {
    private final CropField cropField;
    private final Crop crop;
    private final Wallet wallet;

    public PlantCropCommand(Wallet wallet, CropField cropField, Crop crop) {
        this.wallet = wallet;
        this.cropField = cropField;
        this.crop = crop;
    }

    @Override
    public void execute() {
        if (this.cropField.getState() instanceof NotPlanted) {
            this.cropField.setState(new Planted(this.cropField, this.crop));
            this.wallet.spend(this.crop.getPlantPrice());
        }
    }
}
