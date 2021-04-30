package viewer.farm;

import gui.GUI;
import gui.drawer.entity.FarmerDrawer;
import model.farm.Farmer;

public class FarmerViewer {
    public void draw(Farmer farmer, GUI gui) {
        FarmerDrawer drawer = new FarmerDrawer(gui);
        drawer.draw(farmer.getPosition());
    }
}
