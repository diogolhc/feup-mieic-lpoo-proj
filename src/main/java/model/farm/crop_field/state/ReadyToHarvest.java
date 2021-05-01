package model.farm.crop_field.state;

import model.Position;
import model.farm.crop_field.CropField;
import model.farm.crop_field.crop.Crop;
import model.menu.Button;
import model.menu.Menu;

public class ReadyToHarvest implements CropFieldState {
    private CropField cropField;
    private Crop crop;

    public ReadyToHarvest(CropField cropField, Crop crop) {
        this.cropField = cropField;
    }

    @Override
    public Menu getInteractionMenu() {
        Menu menu = new Menu("READY TO HARVEST");
        menu.addButton(new Button(new Position(1, 5), "HARVEST", 9));
        return menu;
    }
}
