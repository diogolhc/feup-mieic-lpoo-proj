package model.farm.building.crop_field.state;

import model.ChronologicalTime;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.crop.Crop;
import model.menu.Button;
import model.menu.Menu;

public class Planted implements CropFieldState {
    private CropField cropField;
    private Crop crop;
    private ChronologicalTime timeRemaining;

    public Planted(CropField cropField, Crop crop) {
        this.cropField = cropField;
        this.crop = crop;
        timeRemaining = this.crop.getGrowTime();
    }

    @Override
    public ChronologicalTime getRemainingTime() {
        return timeRemaining;
    }
}
