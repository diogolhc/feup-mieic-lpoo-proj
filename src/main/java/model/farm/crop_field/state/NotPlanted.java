package model.farm.crop_field.state;

import model.Position;
import model.farm.crop_field.CropField;
import model.menu.Button;
import model.menu.Menu;

public class NotPlanted implements CropFieldState {
    private CropField cropField;

    public NotPlanted(CropField cropField) {
        this.cropField = cropField;
    }

    @Override
    public Menu getInteractionMenu() {
        Menu menu = new Menu("PLANT");
        menu.addButton(new Button(new Position(1, 5), "WHEAT", 7));
        return menu;
    }
}
