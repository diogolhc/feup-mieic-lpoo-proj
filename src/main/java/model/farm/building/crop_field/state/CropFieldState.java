package model.farm.building.crop_field.state;

import model.ChronologicalTime;
import model.menu.Menu;

public interface CropFieldState {
    ChronologicalTime getRemainingTime();
}
