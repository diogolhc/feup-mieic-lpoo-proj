package viewer;

import gui.GUI;
import gui.drawer.FencesDrawer;
import model.Farm;
import model.Farmer;
import model.Position;

import java.io.IOException;

public class FarmViewer {
    public void draw(Farm farm, GUI gui) throws IOException {
        gui.clear();

        drawFarmer(farm.getFarmer(), new FarmerViewer(), gui);
        drawFences(farm, gui);

        gui.refresh();
    }

    private void drawFarmer(Farmer farmer, FarmerViewer farmerViewer, GUI gui) {
        farmerViewer.draw(farmer, gui);
    }

    private void drawFences(Farm farm, GUI gui) {
        FencesDrawer fencesDrawer = new FencesDrawer(gui);
        fencesDrawer.draw(new Position(0, 0), farm.getWidth(), farm.getHeight());
    }
}
