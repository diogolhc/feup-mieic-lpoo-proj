package model.farm.crop_field.state;

import model.ChronologicalTime;
import model.Position;
import model.farm.crop_field.CropField;
import model.farm.crop_field.crop.Crop;
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
    public Menu getInteractionMenu() {
        Menu menu = new Menu("PLANTED: " + this.crop.toString());
        menu.addButton(new Button(new Position(1, 5), "CANCEL", 7));
        menu.addButton(new Button(new Position(1, 10), "RTH", 7));
        return menu;
    }
}
