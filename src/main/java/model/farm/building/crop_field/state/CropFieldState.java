package model.farm.building.crop_field.state;

import gui.Color;
import model.ChronologicalTime;
import model.farm.building.crop_field.crop.Crop;
import model.menu.Menu;

public interface CropFieldState {
    ChronologicalTime getRemainingTime();
    Crop getCrop();
}
