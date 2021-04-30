package viewer.farm;

import gui.GUI;
import gui.drawer.CropFieldDrawer;
import model.farm.CropField;

public class CropFieldViewer {
    public void draw(CropField cropField, GUI gui) {
        CropFieldDrawer drawer = new CropFieldDrawer(gui);
        drawer.draw(cropField.getPosition());
    }
}
