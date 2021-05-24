package controller.command;

import model.farm.Inventory;
import model.farm.building.CropField;
import model.farm.building.crop_field_state.NotPlanted;
import model.farm.building.crop_field_state.ReadyToHarvest;

public class HarvestCropCommand implements Command {
    private CropField cropField;
    private Inventory inventory;

    public HarvestCropCommand(Inventory inventory, CropField cropField) {
        this.inventory = inventory;
        this.cropField = cropField;
    }

    @Override
    public void execute() {
        if (this.cropField.getState() instanceof ReadyToHarvest) {
            ReadyToHarvest cropState = (ReadyToHarvest) this.cropField.getState();
            this.inventory.storeItem(cropState.getCrop(), cropState.getHarvestAmount());
            this.cropField.setState(new NotPlanted());
        }
    }
}
