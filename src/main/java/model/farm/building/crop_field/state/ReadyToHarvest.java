package model.farm.building.crop_field.state;

import model.ChronologicalTime;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.crop.Crop;
import model.menu.Button;
import model.menu.Menu;

public class ReadyToHarvest implements CropFieldState {
    private Crop crop;

    public ReadyToHarvest(Crop crop) {
        this.crop = crop;
    }

    @Override
    public ChronologicalTime getRemainingTime() {
        return new ChronologicalTime(0);
    }

    @Override
    public Crop getCrop() {
        return this.crop;
    }
}
