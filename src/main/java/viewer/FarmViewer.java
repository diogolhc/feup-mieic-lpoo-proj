package viewer;

import gui.GUI;
import model.Farm;
import model.Farmer;

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
        int width = farm.getWidth();
        int height = farm.getHeight();
        for (int i = 1; i < width-1; i++) {
            gui.drawHorizontalFence(i, 0);
            gui.drawHorizontalFence(i, height-1);
        }
        for (int i = 1; i < height-1; i++) {
            gui.drawVerticalFence(0, i);
            gui.drawVerticalFence(width-1, i);
        }

        gui.drawTopLeftCornerFence(0, 0);
        gui.drawTopRightCornerFence(width-1, 0);
        gui.drawBottomLeftCornerFence(0, height-1);
        gui.drawBottomRightCornerFence(width-1, height-1);
    }
}
