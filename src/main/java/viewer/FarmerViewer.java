package viewer;

import gui.GUI;
import model.Farmer;

public class FarmerViewer {
    public void draw(Farmer farmer, GUI gui) {
        gui.drawFarmer(farmer.getPosition());
    }
}
