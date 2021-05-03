package model.farm.building.crop_field.state;

import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.crop.Crop;
import model.menu.Button;
import model.menu.Menu;

public class ReadyToHarvest implements CropFieldState {
    private CropField cropField;
    private Crop crop;

    public ReadyToHarvest(CropField cropField, Crop crop) {
        this.cropField = cropField;
    }
}
