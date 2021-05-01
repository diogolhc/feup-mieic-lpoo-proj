package viewer.farm;

import gui.GUI;
import gui.drawer.entity.CropFieldDrawer;
import model.farm.crop_field.CropField;

public class CropFieldViewer {
    public void draw(CropField cropField, GUI gui) {
        CropFieldDrawer drawer = new CropFieldDrawer(gui);
        drawer.draw(cropField.getPosition());
    }
}
