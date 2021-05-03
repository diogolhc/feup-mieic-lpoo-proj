package model.farm.building.crop_field.state;

import model.Position;
import model.farm.building.crop_field.CropField;
import model.menu.Button;
import model.menu.Menu;

public class NotPlanted implements CropFieldState {
    private CropField cropField;

    public NotPlanted(CropField cropField) {
        this.cropField = cropField;
    }
}
