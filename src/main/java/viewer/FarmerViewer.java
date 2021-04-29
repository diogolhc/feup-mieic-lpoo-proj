package viewer;

import gui.GUI;
import gui.drawer.FarmerDrawer;
import model.Farmer;

public class FarmerViewer {
    public void draw(Farmer farmer, GUI gui) {
        FarmerDrawer drawer = new FarmerDrawer(gui);
        drawer.draw(farmer.getPosition());
    }
}
