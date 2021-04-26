package viewer;

import gui.GUI;
import model.Farm;
import model.Farmer;

import java.io.IOException;

public class FarmViewer {
    private GUI gui;

    public FarmViewer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Farm farm) throws IOException {
        gui.clear();
        drawFarmer(farm.getFarmer(), new FarmerViewer());
        gui.refresh();
    }

    private void drawFarmer(Farmer farmer, FarmerViewer farmerViewer) {
        farmerViewer.drawFarmer(farmer, gui);
    }
}
