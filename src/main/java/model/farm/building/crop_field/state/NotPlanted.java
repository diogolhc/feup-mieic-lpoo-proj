package model.farm.building.crop_field.state;

import model.ChronologicalTime;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.crop.Crop;
import model.farm.building.crop_field.crop.NoCrop;
import model.menu.Button;
import model.menu.Menu;

public class NotPlanted implements CropFieldState {
    @Override
    public ChronologicalTime getRemainingTime() {
        return new ChronologicalTime(0);
    }

    @Override
    public Crop getCrop() {
        return new NoCrop();
    }
}
