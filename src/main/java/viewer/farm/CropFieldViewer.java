package viewer.farm;

import gui.GUI;
import gui.drawer.entity.CropFieldDrawer;
import model.farm.building.crop_field.CropField;

public class CropFieldViewer {
    public void draw(CropField cropField, GUI gui) {
        CropFieldDrawer drawer = new CropFieldDrawer(gui);
        drawer.draw(cropField.getTopLeftPosition());
    }
}
