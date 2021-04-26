package viewer;

import gui.GUI;
import model.Farm;
import model.Farmer;

import java.io.IOException;

public class FarmViewer {
    public void draw(Farm farm, GUI gui) throws IOException {
        gui.clear();
        drawFarmer(farm.getFarmer(), new FarmerViewer(), gui);
        gui.refresh();
    }

    private void drawFarmer(Farmer farmer, FarmerViewer farmerViewer, GUI gui) {
        farmerViewer.draw(farmer, gui);
    }
}
